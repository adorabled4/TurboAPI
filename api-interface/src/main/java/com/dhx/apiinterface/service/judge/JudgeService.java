package com.dhx.apiinterface.service.judge;

import com.dhx.apicommon.model.v2.param.OJParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.judge.ExecuteCodeResponse;
import com.dhx.apiinterface.domain.judge.ExecuteResult;
import com.dhx.apiinterface.manager.CallResultProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Result;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.dhx.apicommon.common.BaseResponse.TRACE_ID;

/**
 * @author adorabled4
 * @className JudgeService
 * @date : 2024/01/02/ 19:11
 **/
@Service
@Slf4j
public class JudgeService {


    @Resource
    JavaSandboxTemplate javaSandboxTemplate;

    @Resource
    CallResultProducer callResultProducer;

    public void executeJavaCode(OJParam param) {
        ExecuteCodeResponse executeCodeResponse = javaSandboxTemplate.executeJavaCode(param.getInput(), param.getCode());
        List<ExecuteResult> results = executeCodeResponse.getResults();
        log.info("traceId : {} ,result : {}", MDC.get(TRACE_ID), results);
        callResultProducer.callBackResult(ResultUtil.success(results));
    }
}
