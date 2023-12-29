package com.dhx.apicore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author adorabled4
 * @className FreeMarkerConfig
 * @date : 2023/12/28/ 17:48
 **/
@Data
@Component
@ConfigurationProperties(prefix = "template.gen")
public class FreeMarkerConfig {

    /**
     * api-doc的相对路径
     */
    private String docPath;


    /**
     * api-sdk的相对路径
     */
    private String sdkPath;


//    @Bean
//    Configuration configuration() {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
//        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
//        cfg.setDefaultEncoding("UTF-8");
//        return cfg;
//    }

}
