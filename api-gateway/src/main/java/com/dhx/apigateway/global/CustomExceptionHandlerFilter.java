package com.dhx.apigateway.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author adorabled4
 * @className CustomExceptionHandlerFilter
 * @date : 2023/04/22/ 10:30
 **/
@Component
public class CustomExceptionHandlerFilter implements GatewayFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandlerFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(e -> handleError(exchange, e));
    }

    private Mono<Void> handleError(ServerWebExchange exchange, Throwable error) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(error);
        }
        String msg;
        if (error instanceof NotFoundException) {
            msg = "服务未找到";
        } else if (error instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            msg = responseStatusException.getMessage();
        } else {
            msg = "内部服务器错误";
        }
        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), error.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -2;
    }
}

