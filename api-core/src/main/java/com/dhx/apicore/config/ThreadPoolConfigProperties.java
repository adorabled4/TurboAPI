package com.dhx.apicore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author adorabled4
 * @className ThreadPoolConfigProperties
 * @date : 2023/04/19/ 09:58
 **/
@Data
@Component
@ConfigurationProperties(prefix = "api.thread")
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}
