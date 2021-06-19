package com.example.springtouchgo.service.email;


import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class SendEmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String to, String body ,String topic)  {

        try  {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , "utf-8");
            helper.setText(topic,true);
            helper.setFrom("team@touchandgo.com");
            helper.setSubject(topic);
            helper.setTo(to);
            javaMailSender.send(mimeMessage);

        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to send an email");
        }
    }
}
