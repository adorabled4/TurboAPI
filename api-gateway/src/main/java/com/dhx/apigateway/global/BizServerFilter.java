package com.dhx.apigateway.global;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.to.UserTo;
import com.dhx.apicommon.service.InnerUserService;
import com.dhx.apigateway.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author adorabled4
 * @className CustomGlobalFilter
 * @date : 2023/04/19/ 13:41
 **/
@Slf4j
@Component
@Order(1)
public class BizServerFilter implements GlobalFilter {
    @DubboReference
    InnerUserService innerUserService;

    Set<String> whitePath = new HashSet<>();

    @PostConstruct
    private void initPath() {
        whitePath.add("/apicore/user/login");
        whitePath.add("/apicore/user/register");
        whitePath.add("/apicore/login/email");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 通过请求路径判断请求来源
        ServerHttpRequest request = exchange.getRequest();
        UserTo user = null;
        try {
            user = getUserInfo(exchange);
        } catch (RuntimeException e) {
            log(exchange, user);
            throw e;
        }
        return chain.filter(exchange);
    }

    private UserTo getUserInfo(ServerWebExchange exchange) {
        RequestPath path = exchange.getRequest().getPath();
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // 初步判断请求来自前端
        String token = headers.getFirst("Authorization");
        String accessKey = headers.getFirst("accessKey");
        UserTo user = null;
        if (token != null) {
            user = innerUserService.getUserEntityByAccessToken(token, exchange.getResponse().getHeaders());
        } else if (accessKey != null) {
            user = innerUserService.getUserEntityByAccessKey(accessKey);
        } else if (whitePath.contains(path.toString())) {
            return null;
        } else {
//            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        UserHolder.setUser(user);
        return user;
    }

    /**
     * 打印访问日志
     *
     * @param exchange exchange
     * @param user     user
     */
    void log(ServerWebExchange exchange, UserTo user) {
        ServerHttpRequest request = exchange.getRequest();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        if (MediaType.APPLICATION_JSON.isCompatibleWith(request.getHeaders().getContentType())) {
            request.getBody().subscribe(buffer -> {
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
                DataBufferUtils.release(buffer);
                bodyRef.set(charBuffer.toString());
            });
        }
        log.info("[{}],ip:{} ,用户Id:{}, 用户名:{}, 请求路径: {}, 请求参数:{}, 请求体:{}",
                request.getMethodValue(), request.getRemoteAddress().getHostString(),
                user == null ? 0 : user.getUserId(), user == null ? "" : user.getUserName(),
                request.getPath(), request.getQueryParams(), bodyRef.get());
    }

}

