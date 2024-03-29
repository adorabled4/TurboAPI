package com.dhx.apicommon.util;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;

/**
 * 服务器压力指标
 *
 * @author dhx
 * @date 2023/08/23
 */
public class ServerMetricsUtil {
    private static OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    private static ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

    /**
     * 获取当前服务器CPU使用占比
     *
     * @return CPU usage percentage.
     */
    public static double getCpuUsagePercentage() {
        return osBean.getProcessCpuLoad() * 100; // Convert to percentage
    }

    /**
     * 获取当前服务器内存使用占比
     *
     * @return Memory usage percentage.
     */
    public static double getMemoryUsagePercentage() {
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed();
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax();

        return ((double) usedMemory / maxMemory) * 100; // Convert to percentage
    }

    /**
     * 判断当前使用同步还是异步进行服务
     * based on CPU and memory usage.
     *
     * @return true for synchronous, false for asynchronous.
     */
    public static boolean shouldProvideSync() {
        double cpuUsagePercentage = getCpuUsagePercentage();
        double memoryUsagePercentage = getMemoryUsagePercentage();

        // Threshold values for CPU and memory usage
        double cpuThreshold = 70.0; // Example threshold value
        double memoryThreshold = 80.0; // Example threshold value

        if (cpuUsagePercentage < cpuThreshold && memoryUsagePercentage < memoryThreshold) {
            return true; // Provide service synchronously
        } else {
            return false; // Provide service asynchronously
        }
    }
}

