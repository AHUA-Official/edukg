
package com.example.controller;

import com.example.entity.MessageTaskTest;
import com.example.entity.ReceiverInformation;
import com.example.entity.ReceiverInformationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RestController
public class testsendmailController {

    // 从 application.properties 文件中读取邮箱配置信息
    @Value("${mail.smtp.host}")
    private String mailSmtpHost;

    @Value("${mail.smtp.port}")
    private String mailSmtpPort;

    @Value("${mail.smtp.username}")
    private String mailSmtpUsername;

    @Value("${mail.smtp.password}")
    private String mailSmtpPassword;
    //    快速实现功能时的示例代码   该接口是弃用的

    @PostMapping("/sendEmailpastuseless")
    public String sendEmail() {
        ReceiverInformationBuilder   builder = new ReceiverInformationBuilder().withEmail("3309719563@qq.com").withPhone("15282149533").withNeedQuery("false");

        MessageTaskTest  msgtsktst = new MessageTaskTest();
        msgtsktst.setReceiverInformation(builder.build());
        System.out.println(msgtsktst);
        try {
            // 读取 sina.txt 文件内容
            StringBuilder content = new StringBuilder();

            String line;


            content.append("大家好我是天才二次元AHUA");

            // 设置邮箱配置
            Properties props = new Properties();
            props.put("mail.smtp.host", mailSmtpHost);
            props.put("mail.smtp.port", mailSmtpPort);
            props.put("mail.smtp.auth", "true");

            // 创建 Session 对象
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSmtpUsername, mailSmtpPassword);
                }
            });

            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailSmtpUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msgtsktst.getReceiverInformation().getEmail()));
            message.setSubject("Sina Text Content");
            message.setText(content.toString());

            // 发送邮件

            Transport.send(message);

            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
