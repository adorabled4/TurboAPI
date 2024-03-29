package com.dhx.apicore.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author adorabled4
 * @className RedissonConfig
 * @date : 2023/04/23/ 20:04
 **/
@Configuration
@Slf4j
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    RedissonClient redissonClient(){
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL); // 默认是NIO的方式
        config.useSingleServer()
                //可以用"rediss://"来启用SSL连接，前缀必须是redis:// or rediss://
                .setAddress("redis://"+ host+ ":"+port).setPassword(password);
        return Redisson.create(config);
    }
}
