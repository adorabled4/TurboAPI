package com.dhx.apiinterface.manager;

import com.dhx.apicommon.model.v2.param.TranslateParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.enums.PromptEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.dhx.apicommon.common.BaseResponse.TRACE_ID;

/**
 * @author adorabled4
 * @className AigcManager
 * @date : 2024/01/02/ 07:56
 **/
@Component
@Slf4j
public class AigcManager {
    @Resource
    Map<String, BigModelChat> MODEL = new HashMap<>();
    @Resource
    CallResultProducer callResultProducer;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    public void translateToChinese(TranslateParam param) {
        String traceId = MDC.get(TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID, traceId);
        }
        String finalTraceId = traceId;
        threadPoolExecutor.submit(() -> {
            MDC.put(TRACE_ID, finalTraceId);
            BigModelChat model = getModel();
            String input = buildInputByEnum(param.getText(), PromptEnum.TRANSLATE);
            try {
                String result = model.doChat(input);
                callResultProducer.callBackResult(ResultUtil.success(result));
                log.info("traceId : {} ,result : {}", finalTraceId, result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String buildInputByEnum(String input, PromptEnum promptEnum) {
        return promptEnum.getPrefix() + input + promptEnum.getSuffix();
    }

    private BigModelChat getModel() {
        return MODEL.get("QWEN_MAX");
    }
}
