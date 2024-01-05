package com.dhx.apiinterface.jmh;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className BaseBenchMarkTest
 * @date : 2024/01/03/ 23:49
 **/
public abstract class BaseBenchMarkTest {
    protected Options getOptions() {
        String resultFilePrefix = this.getClass().getSimpleName() + "jmh-result";
        ResultFormatType resultsFileOutputType = ResultFormatType.JSON;
        return new OptionsBuilder()
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .forks(1)
                .threads(1) // 测试线程数
                .warmupIterations(1) // 预热迭代次数
                .warmupTime(TimeValue.seconds(1))// 预热迭代的时间
                .warmupBatchSize(10)// 预热迭代次数
                .measurementIterations(100) // 测试迭代次数
                .measurementTime(TimeValue.seconds(1))// 正式测量迭代的时间
                .measurementBatchSize(10) // 每批次测量的大小
                .timeUnit(TimeUnit.MILLISECONDS)
                .resultFormat(resultsFileOutputType)
                .result(buildResultsFileName(resultFilePrefix, resultsFileOutputType))
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .build();
    }

    private static String buildResultsFileName(String resultFilePrefix, ResultFormatType resultType) {
        String suffix = switch (resultType) {
            case CSV -> ".csv";
            case SCSV -> ".scsv";
            case LATEX -> ".tex";
            case JSON -> ".json";
            default -> ".json";
        };
        return String.format("target/%s%s", resultFilePrefix, suffix);
    }
}
