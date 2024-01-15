package com.dhx.apiinterface.jmh;

import cn.hutool.core.util.RandomUtil;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apiinterface.ApiInterfaceApplication;
import com.dhx.apiinterface.controller.Version1Controller;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author adorabled4
 * @className NewV1ApiBenchmarkTest
 * @date : 2024/01/03/ 22:51
 **/
@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@Timeout(time = 120)
public class V1InterfaceBenchmarkTest extends BaseBenchMarkTest {
    private Version1Controller controller;
    ConfigurableApplicationContext context;

    @Setup
    public void setup() {
        if (context == null) {
            context = SpringApplication.run(ApiInterfaceApplication.class);
        }
        if (controller == null) {
            this.controller = this.context.getBean(Version1Controller.class);
        }
    }

    @TearDown
    public void tearDown() {
        // 在测试结束后进行清理工作，比如关闭Spring上下文
        context.close();
    }

    @Benchmark
    public void getRandomPoetTest() {
        this.controller.getRandomPoet();
    }

    @Benchmark
    public void anaIpAddressTest() {
        IpAnaParam ipAnaParam = new IpAnaParam();
        ipAnaParam.setIp(genIpV4Address());
        this.controller.analysisIP(ipAnaParam);
    }

    @Benchmark
    public void getRandomLovelornSentenceTest() {
        this.controller.getRandomLovelornSentence();
    }

    @Benchmark
    public void getSuffixInfoTest() {
        FileSuffixParam fileSuffixParam = new FileSuffixParam();
        fileSuffixParam.setSuffix(getSuffix());
        this.controller.getSuffixDetail(fileSuffixParam);
    }

    @Test
    public void executeJmhRunner() throws RunnerException, IOException {
        new Runner(getOptions()).run();
    }


    public String getSuffix() {
        String[] fileExtensions = {
                ".aac",
                ".accdb",
                ".aif",
                ".aiff",
                ".ani",
                ".apk",
                ".app",
                ".appx",
                ".appxbundle",
                ".art",
                ".asc",
                ".au",
                ".avi",
                ".avif",
                ".bat",
                ".bin",
                ".bmp",
                ".cab",
                ".cad",
                ".cdr",
                ".cfg",
                ".cgm",
                ".class",
                ".cmd",
                ".conf",
                ".css",
                ".cue",
                ".db",
                ".dbf"
        };
        return fileExtensions[RandomUtil.randomInt(0, fileExtensions.length)];

    }

    public String genIpV4Address() {
        String[] ips = {
                "216.58.217.206",
                "151.101.65.121",
                "74.125.24.106",
                "192.0.2.1",
                "23.56.230.156",
                "104.16.248.249",
                "31.13.78.35",
                "204.79.197.200",
                "185.113.25.227",
                "93.184.216.34",
                "151.101.192.81",
                "198.35.26.96",
                "80.250.6.146",
                "89.207.60.135",
                "8.8.8.8",
                "8.8.4.4",
                "198.51.100.1",
                "203.0.113.1",
                "192.0.0.8",
                "192.88.99.1",
                "104.244.42.1",
                "69.171.250.35",
                "69.63.176.13",
                "31.13.65.36",
                "157.240.7.35",
                "157.240.7.51",
                "31.13.92.10",
                "157.240.16.35",
                "157.240.16.42",
                "66.220.144.0"
        };
        return ips[RandomUtil.randomInt(0, ips.length)];
    }
}
