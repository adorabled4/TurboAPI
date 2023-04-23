package com.dhx.apicore.config;

import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author adorabled4
 * @className RedissonConfig
 * @date : 2023/04/23/ 20:04
 **/
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    Config config(){
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL); // 默认是NIO的方式
        String prefix = "redis://";
        config.useClusterServers()
                //可以用"rediss://"来启用SSL连接，前缀必须是redis:// or rediss://
                .addNodeAddress(prefix + host+redisPort);
        return config;
    }
}
