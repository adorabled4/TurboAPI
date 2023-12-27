package com.dhx.apiinterface.v2;

import com.dhx.apiinterface.service.AigcContentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className AIGCTest
 * @date : 2023/12/27/ 11:39
 **/
@SpringBootTest
public class AIGCTest {

    @Resource
    AigcContentService aigcContentService;


    @Test
    @Transactional
    public void Test(){
        aigcContentService.genTakeOutComment(16L,"牛腩拌饭");
    }
}
