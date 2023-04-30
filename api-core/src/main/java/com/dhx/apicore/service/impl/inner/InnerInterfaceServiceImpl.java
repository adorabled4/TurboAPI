package com.dhx.apicore.service.impl.inner;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.service.InnerInterfaceService;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.service.InterfaceEntityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author adorabled4
 * @className InnerInterfaceServiceImpl
 * @date : 2023/04/19/ 14:36
 **/
@DubboService
public class InnerInterfaceServiceImpl implements InnerInterfaceService {

    @Resource
    InterfaceEntityService interfaceEntityService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public InterfaceTo getInterfaceInfo(String url , String method) {
        if(StringUtils.isAnyBlank(method,url)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceEntity> wrapper = new QueryWrapper<>();
        wrapper.like("url", url);
        wrapper.eq("method", method);
        InterfaceEntity one = interfaceEntityService.getOne(wrapper);
        InterfaceTo interfaceTo = BeanUtil.copyProperties(one, InterfaceTo.class);
        return interfaceTo;
    }

    /**
     * 发送消息到 rabbitmq , 异步处理 接口信息统计
     * @param interfaceId
     */
    @Override
    public void interfaceCallCount(long interfaceId) {
        // 统一转换成JSON格式字符串传输
        HashMap<Object, Object> map = new HashMap<>();
        map.put("interfaceId",interfaceId);
        Message message = new Message(JSONUtil.toJsonStr(map).getBytes());
        rabbitTemplate.send(MQConstant.INTERFACE_COUNT_EXCHANGE,MQConstant.INTERFACE_COUNT_QUEUE,message);
    }
}
