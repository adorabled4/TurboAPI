package com.dhx.apigateway.global;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.enums.InterfaceStatusEnum;
import com.dhx.apicommon.model.enums.UserRoleEnum;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.model.to.UserTo;
import com.dhx.apicommon.service.InnerInterfaceService;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicommon.service.InnerUserService;
import com.dhx.apicommon.util.SignUtil;
import com.dhx.apicommon.util.ThrowUtil;
import com.dhx.apigateway.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * sdkrequest过滤器
 *
 * @author adorabled4
 * @className SDKRequestFilter
 * @date 2023/12/25
 */
@Slf4j
@Component
@Order(2)
public class SDKRequestFilter implements GlobalFilter {

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
        // 请求来自SDK,执行对应流程
        if (path.toString().startsWith("/api/")) {
            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            if (route.getMetadata().get("from").equals("frontend_callOL")) {
                return chain.filter(exchange);
            }
            String accessKey = headers.getFirst("accessKey");
            UserTo user = UserHolder.getUser();
            InterfaceTo interfaceInfo = innerInterfaceService.getInterfaceInfo(path.value(), request.getMethodValue());
            validateRequest(headers, accessKey, user, interfaceInfo);
            // 获取路径以及 method
            return handleInvokeInterfaceResponse(exchange, chain, user, interfaceInfo);
        } else {
            return chain.filter(exchange);
        }

    }

    /**
     * 校验调用请求
     *
     * @param headers       请求头
     * @param accessKey     访问密钥
     * @param user          用户
     * @param interfaceInfo 接口信息
     */
    private void validateRequest(HttpHeaders headers, String accessKey, UserTo user, InterfaceTo interfaceInfo) {
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // 时间和当前时间不能超过 5 分钟
        long currentTime = System.currentTimeMillis() / 1000;
        final long FIVE_MINUTES = 60 * 5L;
        assert timestamp != null;
        ThrowUtil.throwIf(StringUtils.isAnyBlank(timestamp, nonce, accessKey, sign), ErrorCode.FORBIDDEN_ERROR, "HMAC signature does not match");

        ThrowUtil.throwIf(currentTime - Long.parseLong(timestamp) >= FIVE_MINUTES, ErrorCode.FORBIDDEN_ERROR, "HMAC signature cannot be verified, a valid date or x-date header is required for HMAC Authentication");

        ThrowUtil.throwIf(user == null, ErrorCode.FORBIDDEN_ERROR, "账号不存在");
        // 校验accessKey
        if (!user.getAccessKey().equals(accessKey)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "HMAC signature cannot be verified");
        }
        if (user.getUserRole().equals(UserRoleEnum.BAN)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该账号已封禁");
        }
        // 校验签名
        // 对比签名是否相同
        String secretKey = user.getSecretKey();
        String serverSign;
        if (body == null) { // 这里如果body为空, 那么会拼接上null字符串
            serverSign = SignUtil.genSign("", secretKey);
        } else {
            serverSign = SignUtil.genSign(body, secretKey);
        }
        ThrowUtil.throwIf(sign == null || !sign.equals(serverSign), ErrorCode.NO_AUTH_ERROR, "非法请求!");
        ThrowUtil.throwIf(user.getLeftCoin() <= 0, ErrorCode.POOR_LEFT_NUM);
        ThrowUtil.throwIf(interfaceInfo == null, ErrorCode.NOT_FOUND_ERROR, "接口不存在");
        ThrowUtil.throwIf(interfaceInfo.getStatus() != InterfaceStatusEnum.AVAILABLE, ErrorCode.PARAMS_ERROR, "当前接口暂时未开启");
    }

    /**
     * 处理调用接口响应
     * 进行用户调用统计 => 执行这个方法的时候, 实际上还没有进行路由, 这里只是添加了(处理响应的)逻辑, 并没有执行
     * 具体来说，当一个请求到达网关服务后，会先经过一系列的过滤器处理，最终被转发到指定的服务提供者。
     * 服务提供者处理完请求后，将响应结果返回给网关服务，此时 handleInvokeInterfaceResponse 方法就会被调用。
     * 该方法对响应结果进行了封装和增强，包括对响应内容进行日志记录、接口调用次数统计等操作。增强后的响应结果会被返回给客户端，完成整个路由请求的过程。
     *
     * @param exchange    交换
     * @param chain       过滤器链
     * @param user        用户信息
     * @param interfaceTo 接口信息
     * @return {@link Mono}<{@link Void}>
     */
    public Mono<Void> handleInvokeInterfaceResponse(ServerWebExchange exchange, GatewayFilterChain chain,
                                                    UserTo user, InterfaceTo interfaceTo) {
        long userId = user.getUserId();
        long interfaceInfoId = interfaceTo.getId();
        //拿到 requet
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse originalResponse = exchange.getResponse();
        // 拿到缓存数据的工厂
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        // ! 拿到响应码 :  注意响应的结果并不是我们的接口的返回结果, 如果接口返回了500 , 那么其实在网关看来还是200 (只要成功响应了, 都是200)
        HttpStatus statusCode = originalResponse.getStatusCode();
        ThrowUtil.throwIf(statusCode != HttpStatus.OK, ErrorCode.NULL_ERROR, "服务响应异常!");
        // 预先保存 callResult 上下文信息
        innerUserInterfaceInfoService.createCallResult(interfaceTo, userId);
        // 装饰, 增强能力
        ServerHttpResponseDecorator successDecoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            // 等调用完转发的接口后才会执行
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    return super.writeWith(Mono.fromDirect(body).map(dataBuffer -> {
                        // 统计接口调用
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);
                        String responseStr = new String(content, StandardCharsets.UTF_8);
                        BaseResponse baseResponse = JSONUtil.toBean(responseStr, BaseResponse.class);
                        boolean isSuccess = baseResponse.getCode() == 200;
                        boolean invokeCount = innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId, interfaceTo.getCost(), isSuccess);
                        ThrowUtil.throwIf(!invokeCount, ErrorCode.OPERATION_ERROR, "更新用户调用异常!");
                        if (isSuccess) {
                            log.info("[callSuccess],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}", request.getRemoteAddress().getHostString(), userId, interfaceInfoId, request.getQueryParams(), new String(content, StandardCharsets.UTF_8));
                            return bufferFactory.wrap(content);
                        } else {
                            log.info("[callFailed],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}", request.getRemoteAddress().getHostString(), userId, interfaceInfoId, request.getQueryParams(), new String(content, StandardCharsets.UTF_8));
                            return bufferFactory.wrap(content);
                        }
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(successDecoratedResponse).build());
    }

}

