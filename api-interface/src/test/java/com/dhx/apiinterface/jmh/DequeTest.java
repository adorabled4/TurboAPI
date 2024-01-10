package com.dhx.apiinterface.jmh;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

import java.util.ArrayDeque;

/**
 * @author adorabled4
 * @className DequeTest
 * @date : 2024/01/08/ 16:17
 **/
@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@Timeout(time = 120)
public class DequeTest extends BaseBenchMarkTest{


    // offer 底层实际上还是调用的 addLast, 因此addLast是更快的
    @Benchmark
    public void offerTest() {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            que.offer(i*i);
        }
    }

    @Benchmark
    public void addLastTest() {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            que.addLast(i*i);
        }
    }
    @Test
    public void Test() throws RunnerException {
        new Runner(getOptions()).run();
    }
}
