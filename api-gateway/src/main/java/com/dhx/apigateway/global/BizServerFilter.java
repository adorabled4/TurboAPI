package com.dhx.apigateway.global;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.to.UserTo;
import com.dhx.apicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * @author adorabled4
 * @className CustomGlobalFilter
 * @date : 2023/04/19/ 13:41
 **/
@Slf4j
@Component
public class BizServerFilter implements GlobalFilter, Ordered {
    @DubboReference
    InnerUserService innerUserService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 通过请求头判断请求来源
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        if (accessKey == null) {
            // 初步判断请求来自前端
            String token = headers.getFirst("Authorization");
            UserTo user = innerUserService.getUserEntityByAccessToken(token, response.getHeaders());
            if (user == null) {
                return handleNoAuth(response);
            }
            log(exchange, user);
        }
        return chain.filter(exchange);
    }

    /**
     * 打印访问日志
     *
     * @param exchange exchange
     * @param user user
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
            String data = new String(content, StandardCharsets.UTF_8); // data
            // 打印日志
            log.info("响应结果：" + data);
            return ServerResponse.ok().build();
        });
        log.info("[{}],ip:{} ,用户Id:{}, 用户名:{}, 请求路径: {}, 请求参数:{}", request.getMethodValue(), request.getRemoteAddress().getHostString(), user.getUserId(), user.getUserName(), request.getPath(), request.getQueryParams());
    }

    /**
     * 非常关键 ! 否则增强的结果不会起作用
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1; // 设置为-1 优先级最高
    }

    /**
     * 处理无权限
     *
     * @param response 响应
     * @return {@link Mono}<{@link Void}>
     */
    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        BaseResponse baseResponse = new BaseResponse<>(ErrorCode.NO_AUTH_ERROR);
        byte[] bytes = JSONUtil.toJsonStr(baseResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes); // 创建数据缓冲区
        return response.writeWith(Mono.just(buffer)); // 写入响应内容
    }
}

