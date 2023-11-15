package org.invest_view.user.repository.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.invest_view.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {

    private final String userName = "egor.chervonikov@yandex.ru";
    private final String password = "wftjdasnfwaqhlow";
    private final String host = "smtp.yandex.ru";
    private final int port = 465;

    public MailService() {
    }

    @Async
    public void sendMessage(User user) {
        String to = user.getEmail();

        Properties properties = new Properties();

        properties.put("mail.host", host);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(userName));
            message.setSubject("Registration to InvestView");
            message.setText("Welcome to InvestView, "+user.getName()+"! To finish registration, please, confirm"
                    +" your email by clicking on the link below. \n"
                    +user.getToken().getToken());

            InternetAddress[] addresses = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, addresses);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
