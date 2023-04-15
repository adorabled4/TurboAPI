package com.dhx.apisdk;

import com.dhx.apisdk.client.HxApiClient;
import com.dhx.apisdk.client.impl.HxApiClientImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className HxApiClientConfig
 * @date : 2023/04/14/ 12:57
 **/
@Configuration
@ConfigurationProperties("dhx.client")
@Data
@ComponentScan
public class HxApiClientConfig {

    public static final String SERVER_HOST= "http://localhost:88";

    private String accessKey;

    private String secretKey;

    @Resource
    HxApiClient hxApiClient;
}
