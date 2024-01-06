package com.dhx.apiinterface.jmh;

import cn.hutool.core.util.RandomUtil;
import com.dhx.apiinterface.ApiInterfaceApplication;
import com.dhx.apiinterface.controller.Version2Controller;
import com.dhx.apiinterface.util.HanZiUtil;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author adorabled4
 * @className V2InterfaceBenchmarkTest
 * @date : 2024-1-6 18:44:33
 **/
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Timeout(time = 120)
public class V2InterfaceBenchmarkTest extends BaseBenchMarkTest {
    private Version2Controller controller;
    ConfigurableApplicationContext context;

    @Setup
    public void setup() {
        if (context == null) {
            context = SpringApplication.run(ApiInterfaceApplication.class);
        }
        if (controller == null) {
            this.controller = this.context.getBean(Version2Controller.class);
        }
    }

    @TearDown
    public void tearDown() {
        // 在测试结束后进行清理工作，比如关闭Spring上下文
        context.close();
    }

    @Benchmark
    public void pyConvertBenchMarkTest(){
         PinyinHelper.toPinyin(HanZiUtil.getRandomHanZiNoSpace(RandomUtil.randomInt(20,4096)), PinyinStyleEnum.DEFAULT);
    }

    @Test
    public void pyConvertTest() {
        assert PinyinHelper.toPinyin("平步青云", PinyinStyleEnum.DEFAULT).equals("píng bù qīng yún");
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


}
