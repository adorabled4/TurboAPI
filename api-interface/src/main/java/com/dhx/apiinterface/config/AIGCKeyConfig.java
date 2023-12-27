package com.dhx.apiinterface.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author adorabled4
 * @className AIGCKeyConfig
 * @date : 2023/12/27/ 10:45
 **/
@Component
@ConfigurationProperties(prefix = "model")
@Data
public class AIGCKeyConfig {

    Map<String, List<String>> keys;

}
