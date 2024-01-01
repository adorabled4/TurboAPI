package com.dhx.apigateway.global;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author adorabled4
 * @className CommonFilter
 * @date : 2024/01/01/ 21:41
 **/
@Order(-1)
@Slf4j
@Component
public class CommonFilter implements GlobalFilter {

    private static final String TRACE_ID = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //对请求对象request进行增强
        ServerHttpRequest req = request.mutate().headers(httpHeaders -> {
            //httpHeaders 封装了所有的请求头
            String traceId = UUID.randomUUID().toString(true);
            MDC.put(TRACE_ID, traceId);
            httpHeaders.set(TRACE_ID, traceId);
            // dubbo context
            RpcContext.getClientAttachment().setAttachment(TRACE_ID,traceId);
        }).build();
        //设置增强的request到exchange对象中
        exchange.mutate().request(req);
        String url = request.getURI().getPath();
        log.info("接收到请求：{}", url);
        // 跨域放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        return chain.filter(exchange);
    }
}
