package com.dhx.apigateway.global;

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
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static com.dhx.apigateway.constant.PathConstant.API_ADMIN_MODULE_PATH;

/**
 * @author adorabled4
 * @className CustomGlobalFilter
 * @date : 2023/04/19/ 13:41
 **/
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    InnerUserService innerUserService;

    @DubboReference
    InnerInterfaceService innerInterfaceService;

    @DubboReference
    InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @Resource
    ThreadPoolExecutor executor;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 通过请求头判断请求来源
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        List<String> sdkList = request.getHeaders().get("dhx.SDK");
        List<String> authorization = request.getHeaders().get("Authorization");
        List<String> apiPlantform = request.getHeaders().get("apiplantform");
        if (path.toString().startsWith(API_ADMIN_MODULE_PATH)) {
            // 后台管理直接放行
            return chain.filter(exchange);
        }
        boolean isFromSDK = false;
        UserTo user = null;
        if (apiPlantform != null) {
            // 请求来自前端 : 通过DUBBO RPC查询用户信息
            if (authorization != null && authorization.size() > 0) { //
                String token = authorization.get(0);
                // exchange 没有实现Serializable , 不能用来传输, 因此这里需要使用HttpHeaders
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders responseHeaders = response.getHeaders();
                user = innerUserService.getUserEntityByAccessToken(token, responseHeaders);
            }
        } else if (sdkList != null && sdkList.size() != 0) {
            // 请求来自SDK , 用户鉴权
            HttpHeaders headers = request.getHeaders();
            String accessKey = headers.getFirst("accessKey");
            String nonce = headers.getFirst("nonce");
            String timestamp = headers.getFirst("timestamp");
            String sign = headers.getFirst("sign");
            String body = headers.getFirst("body");
            user = innerUserService.getUserEntityByAccessKey(accessKey);
            if (StringUtils.isAnyBlank(timestamp, nonce, accessKey, sign)) {
                return handleNoAuth(exchange.getResponse());
            }

            // 判断请求是否合法
            if (user == null) {
                return handleNoAuth(exchange.getResponse());
            }
            if (Long.parseLong(nonce) > 10000L) {
                return handleNoAuth(exchange.getResponse());
            }
            // 时间和当前时间不能超过 5 分钟
            Long currentTime = System.currentTimeMillis() / 1000;
            final Long FIVE_MINUTES = 60 * 5L;
            if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
                return handleNoAuth(exchange.getResponse());
            }
            // 对比  签名是否相同
            String secretKey = user.getSecretKey();
            String serverSign;
            if (body == null) { // 这里如果body为空, 那么会拼接上null字符串
                serverSign = SignUtil.genSign("", secretKey);
            } else {
                serverSign = SignUtil.genSign(body, secretKey);
            }
            if (sign == null || !sign.equals(serverSign)) {
                return handleNoAuth(exchange.getResponse());
            }
            isFromSDK = true;
        } else {
            // 非法请求 , 拦截
            return handleNoAuth(exchange.getResponse());
        }
        // 2. 异步打印日志
        UserTo finalUser = user;
        executor.submit(() -> {
            log(exchange, finalUser);
        });
        // 3. 统计调用信息 写数据库 => 通过DUBBO 远程调用 api-core
        if (isFromSDK) {
            // 获取路径以及 method
            InterfaceTo interfaceInfo = innerInterfaceService.getInterfaceInfo(path.value(), request.getMethodValue());
            if (interfaceInfo == null) { // 请求的接口 没找到 或者 不存在
                return handleSystemError(exchange.getResponse());
            }
            if (user.getUserId() == null) {
                return handleNoAuth(exchange.getResponse());
            }
            // 查看用户的剩余可用次数
            int count = innerUserInterfaceInfoService.getUserLeftNum(user.getUserId(), interfaceInfo.getId());
            if (count <= 0) {
                return handleNoLeftNum(exchange.getResponse());
            }
            return handleInvokeInterfaceResponse(exchange, chain, interfaceInfo.getId(), user.getUserId());
        } else {
            return chain.filter(exchange);
        }
    }


    /**
     * 处理响应
     *
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleInvokeInterfaceResponseMy(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId,
                                                    long userId) {
        try {
            //拿到requet
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓存数据的工厂
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                // 装饰，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 等调用完转发的接口后才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里写数据
                            // 拼接字符串
                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        // 7. 调用成功，接口调用次数 + 1 invokeCount
                                        try {
                                            innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId);
                                        } catch (Exception e) {
                                            log.error("invokeCount error", e);
                                        }
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);// 释放掉内存
                                        // 构建日志
                                        StringBuilder sb2 = new StringBuilder(200);
                                        List<Object> rspArgs = new ArrayList<>();
                                        rspArgs.add(originalResponse.getStatusCode());
                                        String data = new String(content, StandardCharsets.UTF_8); // data
                                        sb2.append(data);
                                        // 打印日志
                                        log.info("[接口调用成功],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}",
                                                request.getRemoteAddress().getHostString(),
                                                userId, interfaceInfoId, request.getQueryParams(),data);
                                        return bufferFactory.wrap(content);
                                    }));
                        } else {
                            // 8. 调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange); // 降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }


    public Mono<Void> handleInvokeInterfaceResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId,
                                                    long userId) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();

        HttpStatus statusCode = originalResponse.getStatusCode();
        if (statusCode != HttpStatus.OK) {
            return chain.filter(exchange);
        }

        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    return super.writeWith(Mono.fromDirect(body).map(dataBuffer -> {
                        innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId);
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);

                        log.info("[接口调用成功],ip:{} ,用户ID:{},  接口ID: {}, 请求参数:{}, 响应结果：{}",
                                request.getRemoteAddress().getHostString(),
                                userId, interfaceInfoId, request.getQueryParams(), new String(content, StandardCharsets.UTF_8));

                        return bufferFactory.wrap(content);
                    }));
                } else {
                    log.error("<--- {} 响应code异常", getStatusCode());
                }
                return super.writeWith(body);
            }
        };

        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }


    /**
     * 打印访问日志
     *
     * @param exchange
     * @param user
     */
    void log(ServerWebExchange exchange, UserTo user) {
        if (user == null) {
            user = new UserTo();
        }
        ServerHttpRequest request = exchange.getRequest();
        Flux<DataBuffer> body = request.getBody();
        body.map(dataBuffer -> {
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer);// 释放掉内存
            // 构建日志
            StringBuilder sb2 = new StringBuilder(200);
            String data = new String(content, StandardCharsets.UTF_8); // data
            sb2.append(data);
            // 打印日志
            log.info("响应结果：" + data);
            return ServerResponse.ok().build();
        });
        log.info("[{}],ip:{} ,用户Id:{}, 用户名:{}, 请求路径: {}, 请求参数:{}",
                request.getMethodValue(), request.getRemoteAddress().getHostString(),
                user.getUserId(), user.getUserName(), request.getPath(), request.getQueryParams());
    }

    /**
     * 非常关键 ! 否则增强的结果不会起作用
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1; // 设置为-1 优先级最高
    }

    /**
     * 处理没有权限
     *
     * @param response
     * @return
     */
    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }


    /**
     *  处理用户次数不足
     * @param response
     * @return
     */
    private Mono<Void> handleNoLeftNum(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.PAYMENT_REQUIRED);
        return response.setComplete();
    }
    /**
     * 处理内部异常
     *
     * @param response
     * @return
     */
    public Mono<Void> handleSystemError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}
