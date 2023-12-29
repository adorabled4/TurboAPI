package com.dhx.apicore.manager;

import cn.hutool.core.date.DateUtil;
import com.dhx.apicore.config.MailConfig;
import com.dhx.apicore.model.enums.MailEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author adorabled4
 * @className EmailManager
 * @date : 2023/12/27/ 17:54
 **/
@Slf4j
@Component
public class EmailManager {

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    MailConfig mailConfig;
    @Resource
    private TemplateEngine templateEngine;

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

    /**
     * 发送富文本验证码
     *
     * @param receiver 接收者
     * @param code     验证码
     * @param request  request
     */
    public void sendHtmlVerifyCode(String receiver, String code, HttpServletRequest request) {
        String visitorIp = request.getRemoteAddr();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfig.getUsername());
            helper.setTo(receiver);
            helper.setSubject("验证您的访问请求");
            Context context = new Context();
            context.setVariable("verificationCode", code);
            context.setVariable("visitorIp", visitorIp);
            context.setVariable("sendTime", DateUtil.format(LocalDateTime.now(), "yyyy-MM-dd"));
            String emailContent = templateEngine.process("mailCodeTemplate", context);
            helper.setText(emailContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
