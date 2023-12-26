package com.dhx.apicore.listener;

import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apicore.model.DO.CallResultEntity;
import com.dhx.apicore.model.DTO.CallResultDTO;
import com.dhx.apicore.model.enums.CallResultEnum;
import com.dhx.apicore.service.CallResultEntityService;
import com.dhx.apicore.util.MQUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author adorabled4
 * @className CallResultListener
 * @date : 2023/08/24/ 10:57
 **/
@Component
@Slf4j
public class CallResultListener {

    @Resource
    CallResultEntityService callResultService;

    /**
     * 保存用户调用接口的情况
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.CALL_RESULT_QUEUE),
            exchange = @Exchange(name = MQConstant.CALL_RESULT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = MQConstant.CALL_RESULT_QUEUE
    ))
    public void callCount(Message message) {
        try {
            CallResultDTO callResultDTO = MQUtil.getData(message, CallResultDTO.class);
            CallResultEntity callResult = new CallResultEntity();
            // 设置参数
            callResult.setCallTime(new Date());
            callResult.setUserId(callResult.getUserId());
            callResult.setInterfaceId(callResultDTO.getInterfaceId());
            // 进行统计
            if (callResultDTO.getBaseResponse().getCode() == 200) {
                callResult.setSucceed(CallResultEnum.SUCCEED.getIsSucceed());
            } else {
                callResult.setSucceed(CallResultEnum.FAILED.getIsSucceed());
            }
            boolean save = callResultService.save(callResult);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"保存用户调用结果失败");
            }
        } catch (RuntimeException e) {
            log.error("统计用户调用结果失败{}", e.getMessage());
        }
    }
}
