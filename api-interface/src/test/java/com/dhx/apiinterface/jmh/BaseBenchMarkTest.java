package com.dhx.apiinterface.jmh;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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
                .threads(4) // 测试线程数
                .warmupIterations(5) // 预热迭代次数
                .measurementIterations(20) // 测试迭代次数
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
