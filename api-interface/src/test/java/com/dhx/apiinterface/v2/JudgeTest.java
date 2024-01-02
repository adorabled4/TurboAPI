package com.dhx.apiinterface.v2;

import com.dhx.apiinterface.service.judge.JavaSandboxTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * @author adorabled4
 * @className JudgeTest
 * @date : 2024/01/02/ 19:01
 **/

@SpringBootTest
public class JudgeTest {
    @Resource
    JavaSandboxTemplate javaSandboxTemplate;

    @Test
    public void Test() throws Exception {
        System.out.println(System.getProperty("os.name"));
        BufferedReader br = new BufferedReader(new FileReader("D:\\j2ee_project\\TurboAPI-backend\\api-interface\\Main.java"));
        char[] buf = new char[2048];
        int len = -1;
        StringBuilder sb = new StringBuilder();
        while ((len = br.read(buf)) != -1) {
            sb.append(new String(buf, 0, len));
        }
        javaSandboxTemplate.executeJavaCode(Arrays.asList("1 2"), sb.toString());
    }

}
