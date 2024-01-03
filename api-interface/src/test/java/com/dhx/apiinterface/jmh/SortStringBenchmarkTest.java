package com.dhx.apiinterface.jmh;

import cn.hutool.core.lang.UUID;
import com.dhx.apiinterface.ApiInterfaceApplication;
import com.dhx.apiinterface.controller.Version1Controller;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className NewV1ApiBenchmarkTest
 * @date : 2024/01/03/ 22:51
 **/
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Timeout(time = 120)
public class SortStringBenchmarkTest extends BaseBenchMarkTest {

    private List<String> list;
    @Setup
    public void setup() {
        list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }
    @TearDown
    public void tearDown() {
        list = null;
    }

    @Benchmark
    public void testSort() {
        Collections.sort(list);
    }


    @Test
    public void testMyBenchmark() throws Exception {
        new Runner(getOptions()).run();
    }

}