package com.dhx.apiinterface.v2;

import com.dhx.apiinterface.gen.AigcContent;
import com.dhx.apiinterface.service.AigcContentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className AIGCTest
 * @date : 2023/12/27/ 11:39
 **/
@SpringBootTest
@Slf4j
public class AIGCTest {

    @Resource
    AigcContentService aigcContentService;

    @Test
    @Transactional
    public void Test() {
        aigcContentService.genTakeOutComment(16L, "牛腩拌饭");
    }

    @Test
    public void genFromText() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("data\\recipes.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] split = sb.toString().split("\n");
            genTakeOutCommentData(split);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void genTakeOutCommentData(String[] recipes) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < recipes.length; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String value = aigcContentService.genTakeOutComment(16L, recipes[finalI]);
                log.info(recipes[finalI] + " : " + value);
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(60, TimeUnit.MINUTES);
    }

    @Test
    public void trimTest() {
        List<AigcContent> list = aigcContentService.list();
        list.forEach(item -> {
            item.setData(item.getData().replace("】", ""));
        });
        aigcContentService.updateBatchById(list);
    }
}
