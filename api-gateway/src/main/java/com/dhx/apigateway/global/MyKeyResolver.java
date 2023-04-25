package com.dhx.apigateway.global;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author adorabled4
 * @className MyKeyResolver
 * @date : 2023/04/24/ 21:25
 **/
@Configuration
public class MyKeyResolver implements KeyResolver {

    /**
     * 1.返回值Mono<String> 泛型中的String就代表令牌分给谁
     * @param exchange
     * @return
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        //根据ip限流
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        return Mono.just(hostAddress);
    }
}
