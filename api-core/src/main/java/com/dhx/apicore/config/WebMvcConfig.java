package com.dhx.apicore.config;

import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.aop.ReFreshTokenInterceptor;
import com.dhx.apicore.service.JwtTokensService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className WebMvcConfig
 * @date : 2023/04/13/ 08:53
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    JwtTokensService jwtTokensService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReFreshTokenInterceptor(stringRedisTemplate,jwtTokensService)).addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/login",
                        "/**/register",
                        "/**/interface/list",
                        "/**/interface/detail/**",
                        "/**/test/**",
                        "/**/interface/example/**",
                        "/**/interface/list/**",
                        "/**/doc.html/**",
                        "/static/**",
                        "/**/swagger-ui.html/**",
                        "/**/favicon.ico",
                        "/**/swagger-resources/**",
                        "/**/webjars/**"
                );
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
