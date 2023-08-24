package com.dhx.apicommon.util;

import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author adorabled4
 * @className MessageUtil MQ工具类 => rabbitmq使用二进制来传输消息
 * @date : 2023/04/27/ 14:35
 **/
public class MQUtil {

    /**
     * 转换 baseResponse 到 byte数组
     *
     * @param baseResponse
     * @return
     */
    public static byte[] result2Byte(BaseResponse baseResponse) {
        String json = JSONUtil.toJsonStr(baseResponse);
        return json.getBytes(StandardCharsets.UTF_8);
    }


    /**
     * 获取MQ message中的参数
     *
     * @param message
     * @return
     */
    public static Map<String, Object> getParamFromMessage(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        Map map = JSONUtil.toBean(s, Map.class);
        return map;
    }

    /**
     * 获取指定类型的数据
     *
     * @param message 消息
     * @param clazz   clazz
     * @return {@link T}
     */
    public static <T> T getData(Message message, Class<T> clazz) {
        byte[] body = message.getBody();
        String string = new String(body, StandardCharsets.UTF_8);
        return JSONUtil.toBean(string, clazz);
    }
}
