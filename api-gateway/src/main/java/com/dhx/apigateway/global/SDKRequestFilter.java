package com.dhx.apigateway.global;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.bo.UserInterfaceInfo;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.model.to.UserTo;
import com.dhx.apicommon.service.InnerInterfaceService;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicommon.service.InnerUserService;
import com.dhx.apicommon.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * sdkrequest过滤器
 *
 * @author adorabled4
 * @className SDKRequestFilter
 * @date 2023/12/25
 */
@Slf4j
@Component
public class SDKRequestFilter implements GlobalFilter, Ordered {

    @DubboReference
    InnerUserService innerUserService;
    @DubboReference
    InnerInterfaceService innerInterfaceService;
    @DubboReference
    InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 通过请求头判断请求来源
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        if (accessKey != null) {
            UserTo user = innerUserService.getUserEntityByAccessKey(accessKey);
            // 请求来自SDK,执行对应流程
            if (!validateUser(headers, accessKey, user)) {
                return handleNoAuth(exchange.getResponse());
            }
            // 获取路径以及 method
            InterfaceTo interfaceInfo = innerInterfaceService.getInterfaceInfo(path.value(), request.getMethodValue());
            // 请求的接口不存在
            if (interfaceInfo == null) {
                return handleNoInterfaceError(exchange.getResponse());
            }
            // 校验用户的剩余可用次数
            int count = innerUserInterfaceInfoService.getUserLeftNum(user.getUserId());
            if (count <= 0) {
                return handleNoLeftNum(exchange.getResponse());
            }
            UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo(interfaceInfo.getId(), user.getUserId());
            return handleInvokeInterfaceResponse(exchange, chain, userInterfaceInfo);
        } else {
            return chain.filter(exchange);
        }

    }

    private boolean validateUser(HttpHeaders headers, String accessKey, UserTo user) {
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        if (StringUtils.isAnyBlank(timestamp, nonce, accessKey, sign)) {
            return false;
        }
        // 判断请求是否合法
        if (user == null) {
            return false;
        }
        if (nonce != null && Long.parseLong(nonce) > 10000L) {
            return false;
        }
        // 时间和当前时间不能超过 5 分钟
        long currentTime = System.currentTimeMillis() / 1000;
        final long FIVE_MINUTES = 60 * 5L;
        if (timestamp != null && (currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return false;
        }
        // 对比签名是否相同
        String secretKey = user.getSecretKey();
        String serverSign;
        if (body == null) { // 这里如果body为空, 那么会拼接上null字符串
            serverSign = SignUtil.genSign("", secretKey);
        } else {
            serverSign = SignUtil.genSign(body, secretKey);
        }
        if (sign == null || !sign.equals(serverSign)) {
            return false;
        }
        return true;
    }

    /**
     * 进行用户调用统计 => 执行这个方法的时候, 实际上还没有进行路由, 这里只是添加了(处理响应的)逻辑, 并没有执行
     * 具体来说，当一个请求到达网关服务后，会先经过一系列的过滤器处理，最终被转发到指定的服务提供者。
     * 服务提供者处理完请求后，将响应结果返回给网关服务，此时 handleInvokeInterfaceResponse 方法就会被调用。
     * 该方法对响应结果进行了封装和增强，包括对响应内容进行日志记录、接口调用次数统计等操作。增强后的响应结果会被返回给客户端，完成整个路由请求的过程。
     *
     * @param exchange
     * @param chain
     * @param userInterfaceInfo
     * @return
     */
    public Mono<Void> handleInvokeInterfaceResponse(ServerWebExchange exchange, GatewayFilterChain chain, UserInterfaceInfo userInterfaceInfo) {
        long interfaceInfoId = userInterfaceInfo.getInterfaceId();
        long userId = userInterfaceInfo.getUserId();
        //拿到 requet
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse originalResponse = exchange.getResponse();
        // 拿到缓存数据的工厂
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        // ! 拿到响应码 :  注意响应的结果并不是我们的接口的返回结果, 如果接口返回了500 , 那么其实在网关看来还是200 (只要成功响应了, 都是200)
        HttpStatus statusCode = originalResponse.getStatusCode();
        if (statusCode != HttpStatus.OK) {
            return handleSystemError(exchange.getResponse());
        }
        // 装饰, 增强能力
        ServerHttpResponseDecorator successDecoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            // 等调用完转发的接口后才会执行
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    return super.writeWith(Mono.fromDirect(body).map(dataBuffer -> {
                        // 统计接口调用
                        innerInterfaceService.interfaceCallCount(interfaceInfoId);
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);
                        String responseStr = new String(content, StandardCharsets.UTF_8);
                        BaseResponse baseResponse = JSONUtil.toBean(responseStr, BaseResponse.class);
                        innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId, baseResponse);
                        if (baseResponse.getCode() == 200) {
                            log.info("[callSuccess],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}", request.getRemoteAddress().getHostString(), userId, interfaceInfoId, request.getQueryParams(), new String(content, StandardCharsets.UTF_8));
                            return bufferFactory.wrap(content);
                        } else {
                            log.info("[callFailed],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}", request.getRemoteAddress().getHostString(), userId, interfaceInfoId, request.getQueryParams(), new String(content, StandardCharsets.UTF_8));
                            return bufferFactory.wrap(content);
                        }
                    }));
                } else {
                    log.error("<--- {} 响应code异常", getStatusCode());
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(successDecoratedResponse).build());
    }


    /**
     * 非常关键 ! 否则增强的结果不会起作用
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 2; // 设置为-1 优先级最高
    }

    /**
     * 处理没有权限
     *
     * @param response
     * @return
     */
    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.NO_AUTH_ERROR);
        byte[] bytes = JSONUtil.toJsonStr(baseResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes); // 创建数据缓冲区
        return response.writeWith(Mono.just(buffer)); // 写入响应内容
    }


    /**
     * 处理用户次数不足
     *
     * @param response
     * @return
     */
    private Mono<Void> handleNoLeftNum(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.PAYMENT_REQUIRED);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.POOR_LEFT_NUM);
        byte[] bytes = JSONUtil.toJsonStr(baseResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes); // 创建数据缓冲区
        return response.writeWith(Mono.just(buffer)); // 写入响应内容
    }

    /**
     * 处理内部异常
     *
     * @param response
     * @return
     */
    public Mono<Void> handleSystemError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.SYSTEM_ERROR);
        byte[] bytes = JSONUtil.toJsonStr(baseResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes); // 创建数据缓冲区
        return response.writeWith(Mono.just(buffer)); // 写入响应内容
    }

    /**
     * 处理内部异常
     *
     * @param response
     * @return
     */
    public Mono<Void> handleNoInterfaceError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.PARAMS_ERROR, "请求错误，接口不存在！");
        byte[] bytes = JSONUtil.toJsonStr(baseResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes); // 创建数据缓冲区
        return response.writeWith(Mono.just(buffer)); // 写入响应内容
    }

}

