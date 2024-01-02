package com.dhx.apiinterface.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.v2.param.OJParam;
import com.dhx.apicommon.model.v2.param.TakeCommentParam;
import com.dhx.apicommon.model.v2.param.TranslateParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicommon.util.ThrowUtil;
import com.dhx.apiinterface.manager.AigcManager;
import com.dhx.apiinterface.service.InvokeInterfaceServiceV2;
import com.dhx.apiinterface.service.judge.JavaSandboxTemplate;
import com.dhx.apiinterface.service.judge.JudgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.dhx.apicommon.common.BaseResponse.TRACE_ID;

/**
 * @author adorabled4
 * @className AIGCController
 * @date : 2023/12/27/ 15:18
 **/
@RestController
@RequestMapping("/v2")
@Slf4j
@Tag(name = "v2接口")
public class Version2Controller {
    @Resource
    InvokeInterfaceServiceV2 invokeInterfaceServiceV2;

    @Resource
    AigcManager aigcManager;
    @Resource
    JudgeService judgeService;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    @GetMapping("/takeout/comment")
    @Operation(summary = "外卖评价生成API")
    public BaseResponse genTakeOutComment(@RequestBody @Validated TakeCommentParam param, HttpServletRequest request) {
        return invokeInterfaceServiceV2.genTakeOutComment(param.getRecipe(), request);
    }

    @PostMapping("/translate")
    @Operation(summary = "AI文本翻译API(Async)")
    public BaseResponse<String> genTranslate(@RequestBody @Validated TranslateParam param) {
        String traceId = genTraceId();
        threadPoolExecutor.submit(() -> {
            MDC.put(TRACE_ID,traceId);
            aigcManager.translateToChinese(param);
        });
        return ResultUtil.success("");
    }

    @PostMapping("/judge/java")
    @Operation(summary = "java判题接口(Async)")
    public BaseResponse<String> judgeOnlineJava(@RequestBody @Validated OJParam param) {
        ThrowUtil.throwIf(!param.getLanguage().equals("Java"), ErrorCode.PARAMS_ERROR, "unavailable language :%s ".formatted(param.getCode()));
        String traceId = genTraceId();
        threadPoolExecutor.submit(() -> {
            MDC.put(TRACE_ID,traceId);
            judgeService.executeJavaCode(param);
        });
        return ResultUtil.success("");
    }

    private String genTraceId() {
        String traceId = MDC.get(TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID, traceId);
        }
        return traceId;
    }
}
