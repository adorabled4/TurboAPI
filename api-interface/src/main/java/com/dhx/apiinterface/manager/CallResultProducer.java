package com.dhx.apiinterface.manager;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.MQConstant;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className CallResultProducer
 * @date : 2024/01/02/ 09:38
 **/
@Component
public class CallResultProducer {
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 发送回调结果
     *
     * @param baseResponse 响应结果
     */
    public void callBackResult(BaseResponse baseResponse) {
        Message message = MessageBuilder.withBody(JSONUtil.toJsonStr(baseResponse).getBytes()).build();
        rabbitTemplate.send(MQConstant.CALL_BACK_RESULT_EXCHANGE, MQConstant.CALL_RESULT_QUEUE, message);
    }

}
