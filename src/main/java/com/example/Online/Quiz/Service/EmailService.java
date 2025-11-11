package com.example.Online.Quiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendQuizResultEmail(String recipient, String quizTitle, int score, int total) {
        try {
            System.out.println("📧 Attempting to send email to: " + recipient);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipient);
            message.setSubject("Your Quiz Results for " + quizTitle);
            message.setText("Hey there!\n\n"
                    + "You completed the quiz: " + quizTitle + "\n"
                    + "Your score: " + score + "/" + total + "\n\n"
                    + "Great job!\n\n- Online Quiz App");

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to " + recipient);
        } catch (Exception e) {
            System.err.println("❌ Failed to send email to " + recipient + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
