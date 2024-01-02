package com.dhx.apiinterface.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v2.param.TakeCommentParam;
import com.dhx.apicommon.model.v2.param.TranslateParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.manager.AigcManager;
import com.dhx.apiinterface.service.AigcContentService;
import com.dhx.apiinterface.service.InvokeInterfaceServiceV2;
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
public class AIGCController {
    @Resource
    InvokeInterfaceServiceV2 invokeInterfaceServiceV2;

    @Resource
    AigcManager aigcManager;

    @GetMapping("/takeout/comment")
    public BaseResponse genTakeOutComment(@RequestBody @Validated TakeCommentParam param, HttpServletRequest request) {
        return invokeInterfaceServiceV2.genTakeOutComment(param.getRecipe(), request);
    }

    @PostMapping("/translate")
    public BaseResponse<String> genTranslate(@RequestBody @Validated TranslateParam param) {
        aigcManager.translateToChinese(param);
        return ResultUtil.success("");
    }
}
