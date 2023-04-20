package com.dhx.apicore;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dhx.apicore.mapper")
@EnableDubbo
public class ApiCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCoreApplication.class, args);
    }

}
