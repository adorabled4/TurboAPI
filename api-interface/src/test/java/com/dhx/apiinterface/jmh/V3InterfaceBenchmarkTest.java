package com.dhx.apiinterface.jmh;

import cn.hutool.core.util.RandomUtil;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apicommon.model.v3.param.ConstellationParam;
import com.dhx.apicommon.model.v3.param.IdiomParm;
import com.dhx.apicommon.model.v3.param.LocationParm;
import com.dhx.apicommon.model.v3.param.QRCodeParam;
import com.dhx.apiinterface.ApiInterfaceApplication;
import com.dhx.apiinterface.controller.Version3Controller;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author adorabled4
 * @className V2InterfaceBenchmarkTest
 * @date : 2024-1-5 20:17:20
 **/
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Timeout(time = 120)
public class V3InterfaceBenchmarkTest extends BaseBenchMarkTest {
    private Version3Controller controller;
    ConfigurableApplicationContext context;

    @Setup
    public void setup() {
        if (context == null) {
            context = SpringApplication.run(ApiInterfaceApplication.class);
        }
        if (controller == null) {
            this.controller = this.context.getBean(Version3Controller.class);
        }
    }

    @TearDown
    public void tearDown() {
        // 在测试结束后进行清理工作，比如关闭Spring上下文
        context.close();
    }

    @Benchmark
    public void anaIdiomTest() {
        IdiomParm param = new IdiomParm();
        param.setWd(getWd());
        this.controller.anaIdiom(param);
    }

    @Benchmark
    public void jqrcodeTest() {
        this.controller.jqrcode(generateRandomQRCodeParam());
    }


    @Benchmark
    public void constellationParamSearchTest() {
        this.controller.constellationParamSearch(ConstellationParam.builder()
                .keyword(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE))
                .build());
    }


    @Benchmark
    public void locationSearchTest() {
        this.controller.locationSearch(genLocationParam());
    }



    @Test
    public void executeJmhRunner() throws RunnerException, IOException {
        new Runner(getOptions()).run();
    }

    private String getWd() {
        String[] fileExtensions = {
                "平步青云",
                "春风得意",
                "乐不思蜀",
                "百花齐放",
                "画龙点睛",
                "千军万马",
                "如鱼得水",
                "四面楚歌",
                "笑逐颜开",
                "金榜题名",
                "心驰神往",
                "独具匠心",
                "心旷神怡",
                "通宵达旦",
                "心灵手巧",
                "左右逢源",
                "心惊肉跳",
                "笑逐颜开",
                "心直口快"
        };
        return fileExtensions[RandomUtil.randomInt(0, fileExtensions.length)];
    }

    public QRCodeParam generateRandomQRCodeParam() {
        String[] possibleErrorLevels = {"h", "q", "m", "l"};
        String randomText = "YourRandomTextHere"; // Replace with actual random text
        String randomEL = possibleErrorLevels[RandomUtil.randomInt(0, possibleErrorLevels.length)];
        String randomBgColor = "ffffff"; // Replace with actual random color
        String randomFgColor = "000000"; // Replace with actual random color
        String randomLogo = "https://example.com/logo.png"; // Replace with actual random logo URL
        Integer randomW = RandomUtil.randomInt(200, 500);
        Integer randomM = 10; // Replace with actual random margin
        Integer randomLW = 60; // Replace with actual random logo width
        Integer randomType = 1;

        return QRCodeParam.builder()
                .text(randomText)
                .el(randomEL)
                .bgcolor(randomBgColor)
                .fgcolor(randomFgColor)
                .logo(randomLogo)
                .w(randomW)
                .m(randomM)
                .lw(randomLW)
                .type(randomType)
                .build();
    }

    private LocationParm genLocationParam() {
        LocationParm locationParm = new LocationParm();
        String[] fids = {"320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "320000", "360000", "360000", "360000", "360000", "360000", "360000", "360000", "360000", "360000", "360000", "360000"};
        locationParm.setFid(fids[RandomUtil.randomInt(0, fids.length)]);
        return locationParm;
    }

}
