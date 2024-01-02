package com.dhx.apiinterface.service.judge;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.WordTree;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apiinterface.domain.judge.ExecuteCodeResponse;
import com.dhx.apiinterface.domain.judge.ExecuteCodeStatusEnum;
import com.dhx.apiinterface.domain.judge.ExecuteResult;
import com.dhx.apiinterface.util.ProcessUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.dhx.apiinterface.constant.SandBoxConstants.JAVA_CLASS_NAME;

/**
 * @author adorabled4
 * @className JavaSandboxTemplate
 * @date : 2024/01/02/ 15:20
 **/
@Slf4j
public abstract class JavaSandboxTemplate {
    private static final WordTree WORD_TREE;

    public String osName;

    @PostConstruct
    private void getOSName() {
        osName = System.getProperty("os.name");
    }

    static {
        WORD_TREE = new WordTree();
        WORD_TREE.addWords("Files", "exec");
    }

    /**
     * 模板方法，定义运行步骤
     */
    public final ExecuteCodeResponse executeJavaCode(List<String> inputList, String code) {
        //1. 把用户的代码保存为文件
        String dir = null;
        File codeFile = null;
        try {
            dir = System.getProperty("user.dir") + File.separator + "tmpCode" + File.separator + UUID.randomUUID();
            codeFile = saveFile(code, dir);
        } catch (BusinessException e) {
            return ExecuteCodeResponse.builder()
                    .code(ExecuteCodeStatusEnum.COMPILE_FAILED.getValue())
                    .msg("Danger code!")
                    .build();
        }
        //2. 编译代码，得到 class 文件
        try {
            ExecuteResult compileMessage = compile(codeFile);
            log.info("编译信息：{}", compileMessage);
            //编译已经失败了
            if (compileMessage.getExitValue() != 0) {
                return ExecuteCodeResponse.builder()
                        .code(ExecuteCodeStatusEnum.COMPILE_FAILED.getValue())
                        .msg(compileMessage.getErrorOutput())
                        .build();
            }
        } catch (IOException e) {
            return ExecuteCodeResponse.builder()
                    .code(ExecuteCodeStatusEnum.COMPILE_FAILED.getValue())
                    .msg(e.toString())
                    .build();
        }

        //3.执行代码，获取结果列表，这一步有不同的实现
        List<ExecuteResult> executeResults;
        try {
            executeResults = runCode(dir, inputList);
            //证明有错误
            ExecuteResult lastResult = executeResults.get(executeResults.size() - 1);
            if (StrUtil.isNotEmpty(lastResult.getErrorOutput())) {
                return ExecuteCodeResponse.builder()
                        .code(ExecuteCodeStatusEnum.RUN_FAILED.getValue())
                        .msg(lastResult.getErrorOutput())
                        .build();
            }
        } catch (IOException e) {
            return ExecuteCodeResponse.builder()
                    .code(ExecuteCodeStatusEnum.RUN_FAILED.getValue())
                    .msg(e.toString())
                    .build();
        }
        log.info(executeResults.toString());
        //4. 收集整理输出结果
        ExecuteCodeResponse response = arrangeResponse(executeResults);
        //5. 清理文件
        clearFile(codeFile, dir);
        return response;
    }

    /**
     * 将code保存在dir目录下
     *
     * @param code
     * @param dir
     * @return
     */
    protected File saveFile(String code, String dir) {
        //检查代码内容，是否有黑名单代码
        FoundWord foundWord = WORD_TREE.matchWord(code);
        if (foundWord != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Danger Code!");
        }
        String path = dir + File.separator + JAVA_CLASS_NAME;
        log.info("保存目录：{}", dir);
        return FileUtil.writeUtf8String(code, path);
    }

    /**
     * 对codeFile进行编译
     *
     * @param codeFile
     * @return
     * @throws IOException
     */
    protected ExecuteResult compile(File codeFile) throws IOException {
        String compileCmd = null;
        if (osName.contains("win")) {
            // win
            compileCmd = String.format("javac -encoding utf-8 %s", codeFile.getAbsolutePath());
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            // Linux
            compileCmd = String.format("/software/jdk1.8.0_361/bin/javac -encoding utf-8 %s", codeFile.getAbsolutePath());
        } else {
            // other
            log.info("unknown system name: {}", osName);
        }
        log.info("执行命令：{}", compileCmd);
        Process compileProcess = Runtime.getRuntime().exec(compileCmd);
        //拿到process执行信息
        return ProcessUtils.getProcessMessage(compileProcess, "编译");
    }

    /**
     * 运行代码，这一步针对Args和ACM有不同实现
     *
     * @param dir
     * @param inputList
     * @return
     */
    protected abstract List<ExecuteResult> runCode(String dir, List<String> inputList) throws IOException;

    /**
     * 收集整理运行结果
     *
     * @param executeResults
     * @return
     */
    protected ExecuteCodeResponse arrangeResponse(List<ExecuteResult> executeResults) {
        return ExecuteCodeResponse.builder()
                .code(ExecuteCodeStatusEnum.SUCCESS.getValue())
                .msg(ExecuteCodeStatusEnum.SUCCESS.getMsg())
                .results(executeResults).build();
    }

    /**
     * 文件清理
     *
     * @param codeFile
     * @param dir
     */
    protected void clearFile(File codeFile, String dir) {
        if (codeFile.getParentFile() != null) {
            boolean del = FileUtil.del(dir);
            log.info("删除{}: {}", del ? "成功" : "失败", dir);
        }
    }
}

