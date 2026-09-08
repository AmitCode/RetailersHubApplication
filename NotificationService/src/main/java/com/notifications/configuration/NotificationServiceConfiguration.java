package com.notifications.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class NotificationServiceConfiguration {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

//    @Value("${spring.mail.properties.mail.transport.protocol}")
//    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

//    @Value("${spring.mail.properties.mail.debug}")
//    private String debug;

    @Bean
    public JavaMailSender getEmailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        System.out.println("Email: " + username + ", Password: " +password);
        Properties props = mailSender.getJavaMailProperties();
        //props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", starttls);
        //props.put("mail.debug", debug);

        return mailSender;
    }
//    public JavaMailSender getJavaMailSender(){
//        return new JavaMailSenderImpl();
//    }
}
