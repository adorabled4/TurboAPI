package com.dhx.apicore.listener;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.MQConstant;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicore.model.DO.CallBack;
import com.dhx.apicore.model.DO.CallResult;
import com.dhx.apicore.model.enums.CallApiStatusEnum;
import com.dhx.apicore.service.CallBackService;
import com.dhx.apicore.service.CallResultService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.representer.BaseRepresenter;

import javax.annotation.Resource;

import java.io.IOException;

import static com.dhx.apicommon.common.BaseResponse.TRACE_ID;

/**
 * @author adorabled4
 * @className CallResultListener
 * @date : 2024/01/02/ 09:26
 **/
@Component
@Slf4j
public class CallResultListener {

    @Resource
    CallResultService callResultService;

    @Resource
    CallBackService callBackService;

    /**
     * 接收异步接口执行结果
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = MQConstant.CALL_RESULT_QUEUE),
            exchange = @Exchange(name = MQConstant.CALL_BACK_RESULT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = MQConstant.CALL_RESULT_QUEUE))
    public void callResultSendListener(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliverTag) throws IOException {
        String traceId = MDC.get(TRACE_ID);
        try{
            log.info("receive message :{}", message);
            if (StringUtils.isBlank(message)) {
                channel.basicNack(deliverTag, false, false);
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接收到的消息为空!");
            }
            // 查询config
            BaseRepresenter invokeResult = JSONUtil.toBean(message, BaseRepresenter.class);
            CallResult callResult = callResultService.findByTraceId(traceId);
            CallBack callBack = callBackService.findCallBackConfig(callResult.getInterfaceId(),callResult.getUserId());
            String callBackUrl = callBack.getCallBackUrl();
            // 发送结果
            HttpRequest.post(callBackUrl).body(invokeResult.toString()).executeAsync();
            // 更新调用状态
            callResultService.updateCallStatus(traceId, CallApiStatusEnum.SUCCEED);
        }catch (Exception e){
            callResultService.updateCallStatus(traceId, CallApiStatusEnum.FAILED);
        }
    }


}
