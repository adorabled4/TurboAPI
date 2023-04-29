package com.dhx.apicore.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
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

    @Bean
    RedissonClient redissonClient(){
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL); // 默认是NIO的方式
        config.useSingleServer()
                //可以用"rediss://"来启用SSL连接，前缀必须是redis:// or rediss://
                .setAddress("redis://8.130.79.173:6379").setPassword("r324fhh2n3084brj2w");
        return Redisson.create(config);
    }
}
