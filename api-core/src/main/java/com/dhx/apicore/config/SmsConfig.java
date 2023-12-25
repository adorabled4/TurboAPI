package com.dhx.apicore.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className SmsConfig
 * @date : 2023/07/05/ 17:53
 **/
@Configuration
@Data
public class SmsConfig {
    @Value("${sms.endpoint}")
    private String endpoint;

    @Value("${sms.accessId}")
    private String accessId;

    @Value("${sms.accessKey}")
    private String accessKey;

    private String successCode="OK";

    /**
     * 模板名称
     */
    @Value("${sms.commonSignName}")
    private String commonSignName ="**********" ;

    /**
     * 模板CODE
     */
    @Value("${sms.templateCodeTwo}")
    private String templateCodeTwo ="*************";

    @Bean
    Client client(){
        Client client = null;
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKey);
        config.endpoint = endpoint;
        config.setEndpoint(endpoint);
        try {
            client = new Client(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

}
