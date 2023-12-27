package com.dhx.apicore.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author adorabled4
 * @className OSSConfig
 * @date : 2023/12/27/ 20:18
 **/
@Configuration
public class OSSConfig {

    @Value("${alibaba.cloud.access-key}")
    String accessKey;

    @Value("${alibaba.cloud.secret-key}")
    String secretKey;

    @Value("${alibaba.cloud.oss.endpoint}")
    String endpoint;

    @Value("${alibaba.cloud.oss.bucket}")
    String bucket;

    @Value("${alibaba.cloud.oss.domain}")
    String domain;


    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKey, secretKey);
    }

    public String getBucket() {
        return bucket;
    }


    public String getUrlPrefix() {
        return domain;
    }
}
