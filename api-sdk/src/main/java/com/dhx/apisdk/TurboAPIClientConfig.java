package com.dhx.apisdk;

import com.dhx.apisdk.client.TurboAPIClient;
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
public class TurboAPIClientConfig {

    public static final String SERVER_HOST = "http://localhost:88";

    private String accessKey;

    private String secretKey;

    @Bean
    TurboAPIClient turboAPIClient(){
        return new TurboAPIClient(accessKey,secretKey);
    }
}
