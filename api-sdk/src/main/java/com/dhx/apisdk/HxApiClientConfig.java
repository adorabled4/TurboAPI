package com.dhx.apisdk;

import com.dhx.apisdk.client.HxApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    private String accessKey;

    private String secretKey;

    @Bean
    public HxApiClient hxApiClient() {
        return new HxApiClient(accessKey, secretKey);
    }
}
