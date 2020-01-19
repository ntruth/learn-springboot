package com.ucmed.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("email测试");
        message.setText("有检测室内容");
        message.setTo("xxxxxxx@qq.com");
        message.setFrom("xxxxxxx@qq.com");
        javaMailSenderImpl.send(message);
    }

    public void sendComplicated() throws MessagingException {
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("email测试11111");
        mimeMessageHelper.setText("有检测室内容1111");
        mimeMessageHelper.setTo("xxxxxx@qq.com");
        mimeMessageHelper.setFrom("xxxxxxx@qq.com");
        // 可以添加附件
        // mimeMessageHelper.addAttachment("fileName", new File("Your File Path"));
        javaMailSenderImpl.send(message);
    }

}
