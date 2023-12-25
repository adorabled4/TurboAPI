package com.dhx.apicore.manager;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.dhx.apicore.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className SMSUtils
 * @date : 2023/07/05/ 17:55
 **/
@Slf4j
@Component
public class SMSManager {

    @Resource
    Client client;

    @Resource
    SmsConfig smsConfig;

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return boolean
     */
    public String send(String phone) {
        String code = RandomUtil.randomNumbers(6);
        SendSmsRequest request = createRequest(phone, JSONUtil.toBean(code, JSONObject.class).toString());
        try {
            SendSmsResponse response = client.sendSms(request);
            JSONObject object = JSONUtil.toBean(JSONUtil.toJsonStr(response), JSONObject.class);
            if (object.get("code").equals(smsConfig.getSuccessCode())) {
                log.info("阿里云短信发送成功！手机号：【{}】 -- 验证码：【{}", phone, code);
                return code;
            }
        } catch (Exception e) {
            log.error("阿里云短信发送出现异常：{}", e.getMessage());
            return "";
        }
        log.info("阿里云短信发送失败！手机号：【{}】 -- 验证码：【{}】", phone, code);
        return "";
    }

    /**
     * 创建请求
     *
     * @param mobile 接受手机号
     * @return SendSmsRequest
     */
    private SendSmsRequest createRequest(String mobile, String param) {
        return new SendSmsRequest()
                .setPhoneNumbers(mobile)
                .setSignName(smsConfig.getCommonSignName())//此填写签名名称
                .setTemplateCode(smsConfig.getTemplateCodeTwo())//此填写模板CODE
                .setTemplateParam(param);//验证码参数为json字符串格式 {"code":"xxxxxx"}
    }
}
