package com.dhx.apicore.service.impl.inner;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.model.to.UserTo;
import com.dhx.apicommon.service.InnerUserService;
import com.dhx.apicore.constants.JwtConstant;
import com.dhx.apicore.constants.RedisConstant;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.service.JwtTokensService;
import com.dhx.apicore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className InnerUserServiceImpl
 * @date : 2023/04/19/ 13:56
 **/
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    JwtTokensService jwtTokensService;

    @Resource
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public UserTo getUserEntityByAccessToken(String token, HttpHeaders headers) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        // 解析并验证JWT token是否合法
        boolean isTokenExpired = jwtTokensService.isTokenExpired(token);
        UserEntity user = jwtTokensService.validateToken(token);
        UserEntity userEntity = userService.getById(user);
        if(user==null){
            return null;
        }
        UserTo userTo = BeanUtil.copyProperties(userEntity, UserTo.class);
        if(isTokenExpired){
            // 如果token过期 , 那么需要通过refresh_token生成一个新的access_token
            String refreshTokenKey = RedisConstant.REFRESH_TOKEN_PREFIX+ user.getUserId();
            String refreshToken = stringRedisTemplate.opsForValue().get(refreshTokenKey);
            if(StringUtils.isEmpty(refreshToken)){
                return null;
            }
            if(jwtTokensService.isTokenExpired(refreshToken)){
                return null;
            }
            // 生成新的accessToken , 同时保存到redis
            String accessToken = jwtTokensService.generateAccessToken(user);
            String accessTokenKey = RedisConstant.ACCESS_TOKEN_PREFIX +user.getUserId();
            stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,
                    JwtConstant.EXPIRATION_TIME, TimeUnit.SECONDS);
            headers.set("Authorization",accessToken);
        }
        // 返回userTO
        return userTo;
    }

    @Override
    public UserTo getUserEntityByAccessKey(String accessKey) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("access_key",accessKey);
        UserEntity user = userService.getOne(wrapper);
        return BeanUtil.copyProperties(user,UserTo.class);
    }
}
