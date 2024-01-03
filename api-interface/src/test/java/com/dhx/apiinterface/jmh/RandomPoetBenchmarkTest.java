package com.dhx.apiinterface.jmh;

import com.dhx.apiinterface.ApiInterfaceApplication;
import com.dhx.apiinterface.controller.Version1Controller;
import org.junit.jupiter.api.BeforeEach;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className NewV1ApiBenchmarkTest
 * @date : 2024/01/03/ 22:51
 **/
//@SpringBootTest // 使用SpringBootTest加载Spring上下文
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Timeout(time = 120)
public class RandomPoetBenchmarkTest extends SpringBaseBenchMarkTest {
    private Version1Controller controller;

    @Benchmark
    public void test() {
        this.controller.getRandomPoet();
    }

    @BeforeEach
    public void getBean() {
        this.controller = this.context.getBean(Version1Controller.class);
    }

    @Test
    public void executeJmhRunner() throws RunnerException, IOException {
        new Runner(getOptions()).run();
    }

}
