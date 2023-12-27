package com.dhx.apicore.manager;

import cn.hutool.core.date.DateUtil;
import com.dhx.apicore.config.MailConfig;
import com.dhx.apicore.model.enums.MailEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className EmailManager
 * @date : 2023/12/27/ 17:54
 **/
@Slf4j
public class EmailManager {

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    MailConfig mailConfig;

    public boolean sendVerifyCode(String receiver, String code, MailEnum mailEnum) {
        // 拼接内容
        String content = mailEnum.getPrefix() + code + mailEnum.getSuffix();
        // 设置信息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getUsername());
        message.setTo(receiver);
        message.setSubject(mailEnum.getTitle());
        message.setText(content);
        // 发送邮件
        javaMailSender.send(message);
        log.info("邮件发送成功！to：【{}】 -- 验证码：【{}】 -- {}", receiver, code, DateUtil.now());
        return true;
    }

}
