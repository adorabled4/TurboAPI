package com.dhx.apiinterface.service.judge;

import com.dhx.apiinterface.domain.judge.ExecuteResult;
import com.dhx.apiinterface.util.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.dhx.apiinterface.constant.SandBoxConstants.TIME_OUT;

/**
 * @author adorabled4
 * @className JavaNativeArgsSandbox
 * @date : 2024/01/02/ 18:44
 **/
@Service
@Slf4j
public class JavaNativeArgsSandbox extends JavaSandboxTemplate {

    @Override
    protected List<ExecuteResult> runCode(String dir, List<String> inputList) throws IOException {
        List<ExecuteResult> messages = new ArrayList<>();
        for (String input : inputList) {
            String runCmd = null;
            if (osName.contains("win")) {
                // win
                runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", dir, input);
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                // Linux
                runCmd = String.format("/software/jdk1.8.0_361/bin/java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", dir, input);
            } else {
                // other
                log.info("unknown system name: {}", osName);
            }
            Process runProcess = Runtime.getRuntime().exec(runCmd);
            new Thread(() -> {
                try {
                    Thread.sleep(TIME_OUT);
                    System.out.println("超时了，中断");
                    runProcess.destroy();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            ExecuteResult executeResult = ProcessUtils.getProcessMessage(runProcess, "运行");
            messages.add(executeResult);
        }
        return messages;
    }
}
