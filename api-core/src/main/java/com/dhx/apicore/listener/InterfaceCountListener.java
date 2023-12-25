package com.dhx.apicore.listener;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.service.InterfaceEntityService;
import com.dhx.apicore.util.MQUtil;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author adorabled4
 * @className InterfaceCountListener
 * @date : 2023/04/30/ 10:12
 **/
@Component
public class InterfaceCountListener {

    @Resource
    InterfaceEntityService interfaceEntityService;

    /**
     * 更新 接口 的总调用次数
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.INTERFACE_COUNT_QUEUE),
            exchange = @Exchange(name =MQConstant.INTERFACE_COUNT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key=MQConstant.INTERFACE_COUNT_QUEUE
    ))
    public void callCount(Message message){
        Map<String, Object> param = MQUtil.getParamFromMessage(message);
        Object data = param.get("interfaceId");
        if(data instanceof Integer){
            Integer interfaceId = ((Integer) data);
            Long id = Long.valueOf(interfaceId);
            UpdateWrapper<InterfaceEntity> wrapper = new UpdateWrapper<>();
            wrapper.setSql("call_times = call_times +1");
            wrapper.eq("id",id);
            // 更新调用次数
            interfaceEntityService.update(wrapper);
            // 更新redis排行榜
            interfaceEntityService.addRankScore(id);
        }
    }

}
