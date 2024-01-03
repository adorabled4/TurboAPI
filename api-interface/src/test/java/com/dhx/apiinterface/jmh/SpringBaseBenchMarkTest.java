package com.dhx.apiinterface.jmh;

import com.dhx.apiinterface.ApiInterfaceApplication;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className BaseBenchMarkTest
 * @date : 2024/01/03/ 23:49
 **/
public abstract class SpringBaseBenchMarkTest extends BaseBenchMarkTest{
    ConfigurableApplicationContext context;

    @Setup
    public void setup() {
        context = SpringApplication.run(ApiInterfaceApplication.class);
    }

    @TearDown
    public void tearDown() {
        // 在测试结束后进行清理工作，比如关闭Spring上下文
        context.close();
    }

}
