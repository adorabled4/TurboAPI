package com.dhx.apicore.manager;

import cn.hutool.core.date.DateUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

/**
 * @author adorabled4
 * @className EmailTest
 * @date : 2023/12/27/ 18:30
 **/
@SpringBootTest
public class EmailTest {
    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    public void sendVerificationCodeEmail() throws MessagingException {
        String to = "2648466390@qq.com";
        String visitorIp = "114.23.43.1";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setTo(to);
            helper.setSubject("验证您的访问请求");
            Context context = new Context();
            context.setVariable("verificationCode", 342133);
            context.setVariable("visitorIp", visitorIp);
            context.setVariable("sendTime", DateUtil.format(LocalDateTime.now(),"yyyy-MM-dd"));
            String emailContent = templateEngine.process("mailCodeTemplate", context);
            System.out.println(emailContent);
            helper.setText(emailContent, true);
            javaMailSender.send(message);
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
