package com.dhx.apiinterface.aop;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author adorabled4
 * @className LogAOP
 * @date : 2024/01/01/ 21:47
 **/
@Component
@Aspect
@Slf4j
public class LogAOP {


    ObjectMapper mapper = new ObjectMapper();

    private static final String TRACE_ID = "traceId";

    /**
     * 外部接口调用的日志监控
     *
     * @param joinPoint 连接点
     * @return {@link Object}
     */
    @Around(value = "execution(* com.dhx.apiinterface.controller..*.* (..))")
    public Object doRequestAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 日志链路
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        String traceId = request.getHeader(TRACE_ID);
        MDC.put(TRACE_ID, traceId);
        // 参数打印
        Object result;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String name = method.getName();
        Object[] args = joinPoint.getArgs();
        Object object = joinPoint.getTarget();
        log.info("class :{}, method :{}, param :{}", object.getClass().getName(), name, mapper.writeValueAsString(args));
        result = joinPoint.proceed();
        log.info("class :{}, method :{}, result :{}", object.getClass().getName(), name, genResultString(result));
        return result;
    }

    /**
     * 创结果字符串
     *
     * @param result 结果
     * @return {@link String}
     */
    private String genResultString(Object result) {
        //如果结果为空，只直接返回
        if (result == null) {
            return null;
        }
        String val = JSONUtil.toJsonStr(result);
        if (val.length() > 1024) {
            return val.substring(0, 1023);
        }
        return val;
    }

}
