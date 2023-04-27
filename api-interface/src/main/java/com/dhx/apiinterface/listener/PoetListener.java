package com.dhx.apiinterface.listener;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apiinterface.service.IPoetService;
import com.dhx.apiinterface.util.MQUtil;
import com.dhx.apiinterface.vo.PoetVO;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author adorabled4
 * @className PoetListener
 * @date : 2023/04/25/ 21:50
 **/
@Component
public class PoetListener {

    @Resource
    IPoetService poetService;

    /**
     * 通过注解声明 交换机以及绑定的关系
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.RANDOM_POET_QUEUE),
            exchange = @Exchange(name =MQConstant.INTERFACE_ROUTE_EXCHANGE, type = ExchangeTypes.DIRECT),
            key=MQConstant.RANDOM_POET_QUEUE
    ))
    public byte[] randomPoet(){
        // 直接返回 会被转换成二进制数据 (rabbitmq默认) , 因此需要指定字符集, 与接收方统一字符集 , 然后返账
        return MQUtil.result2Byte(poetService.getRandomPoetVo());
    }


//    /**
//     * 声明交换机
//     * @return direct类型交换机
//     */
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange(MQConstant.INTERFACE_ROUTE_EXCHANGE);
//    }

//    /**
//     * 定义队列
//     */
//    @Bean
//    public Queue randomPoetQueue(){
//        return new Queue(MQConstant.RANDOM_POET_QUEUE);
//    }

}
