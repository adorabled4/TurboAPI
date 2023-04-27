package com.dhx.apiinterface.listener;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.service.IPoetService;
import com.dhx.apiinterface.util.IpUtil;
import com.dhx.apiinterface.util.MQUtil;
import com.dhx.apiinterface.vo.PoetVO;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author adorabled4
 * @className IPListener
 * @date : 2023/04/27/ 14:28
 **/
@Component
public class IPListener {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.IP_ANALYSIS_QUEUE),
            exchange = @Exchange(name =MQConstant.INTERFACE_ROUTE_EXCHANGE, type = ExchangeTypes.DIRECT),
            key=MQConstant.IP_ANALYSIS_QUEUE
    ))
    public byte[] analysisIP(Message message){
        Map<String, Object> param = MQUtil.getParamFromMessage(message);
        Object ip = param.get("ip");
        if(ip instanceof JSONArray){
            String ipAddr = ((JSONArray) ip).get(0).toString();
//            String ipAddr=(String)ip; 强制转换不行 , 经过序列化传输的 IP 类名是 JsonArray
            if(StringUtils.isEmpty(ipAddr)){
                return MQUtil.result2Byte(ResultUtil.error());
            }
            // 正常逻辑
            try{
                InetAddress inetAddress = InetAddress.getByName(ipAddr);
                if (inetAddress instanceof Inet4Address) {
                    String location = IpUtil.getIpLocation(ipAddr);
                    BaseResponse<String> success = ResultUtil.success(location);
                    return MQUtil.result2Byte(success);
                } else {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不是合法的IPv4地址!");
                }
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }else{
            return MQUtil.result2Byte(ResultUtil.error());
        }
    }

}
