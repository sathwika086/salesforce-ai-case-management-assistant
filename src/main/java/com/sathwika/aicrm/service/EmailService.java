package com.sathwika.aicrm.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResolutionEmail(
        String toEmail,
        String caseId,
        String category,
        String resolution) {

    try {

        SimpleMailMessage message =
                new SimpleMailMessage();
        message.setFrom("aicrm.sathwika@gmail.com");

        message.setTo(toEmail);

        message.setSubject(
                "Your Support Case Has Been Resolved");

        message.setText(
                "Hello,\n\n" +
                "Your support case has been resolved.\n\n" +
                "Case ID: " + caseId + "\n" +
                "Category: " + category + "\n\n" +
                "Resolution:\n" +
                resolution +
                "\n\nThank you for contacting support."
        );

        mailSender.send(message);

        System.out.println("EMAIL SENT TO: " + toEmail);

    } catch (Exception e) {

        System.out.println("EMAIL ERROR: " + e.getMessage());
        e.printStackTrace();
    }
}
}