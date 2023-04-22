package com.dhx.apiinterface.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className IpUtil
 * @date : 2023/04/15/ 15:52
 **/
@Slf4j
public class IpUtil {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            if (!StringUtils.isEmpty(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        } catch (Exception e) {
        }
        return ip;
    }

    public static String getIpLocation(String ip) {
        // 1、创建 searcher 对象
        Searcher searcher = null;
        String dbPath = "ip2region.xdb";
        try {
            Resource resource = new ClassPathResource(dbPath);
            InputStream inputStream = resource.getInputStream();
            File file = File.createTempFile("ip2region", ".xdb");
            FileUtils.copyInputStreamToFile(inputStream, file);
            searcher = Searcher.newWithFileOnly(file.getAbsolutePath());
        } catch (IOException e) {
            log.error("failed to create searcher with `" + dbPath +"`:"  + e);
            return "";
        }
        // 2、查询
        String region = "";
        try {
            long sTime = System.nanoTime();
            region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
//            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            // 3、关闭资源
            searcher.close();
        } catch (Exception e) {
            log.error("failed to search(" + dbPath + "): " + e);
        }
        return region;
    }
}
