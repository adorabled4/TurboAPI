package com.dhx.apigateway.constant;

import com.dhx.apicommon.constant.MQConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adorabled4
 * @className Path2Queue 请求路径 : 消息队列
 * @date : 2023/04/25/ 21:45
 **/
public class Path2Queue {
    public static final Map<String,String> data;
    static {
        data=new HashMap<>();
        data.put("/dev-api/api/apiinterface/poet/random", MQConstant.RANDOM_POET_QUEUE);
        data.put("/dev-api/api/ip/ana", MQConstant.IP_ANALYSIS_QUEUE);
    }
}
