package com.dhx.apicore.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;
import java.util.UUID;

/**
 * @author adorabled4
 * @className TraceFilter
 * @date : 2024/01/01/ 22:36
 **/
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER}, order = -30000)
@Slf4j
public class RemoteTraceIdFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 消费者
        if (RpcContext.getCurrentServiceContext().isConsumerSide()) {
            String traceId = MDC.get(TRACE_ID);
            if(traceId==null){
                traceId = UUID.randomUUID().toString();
            }
            //消费者 将trace_id（业务流水号） set至上下文中
            RpcContext.getClientAttachment().setAttachment(TRACE_ID, traceId);
        } else {
            // 服务提供者
            String traceId = RpcContext.getClientAttachment().getAttachment(TRACE_ID);
            if (traceId == null) {
                traceId = UUID.randomUUID().toString();
            }
            //slf4j 中设置了日志打印格式用作日志链路追踪
            MDC.put(TRACE_ID, traceId);
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            if (RpcContext.getCurrentServiceContext().isProviderSide()) {
                MDC.remove(TRACE_ID);
            }
        }
    }
}
