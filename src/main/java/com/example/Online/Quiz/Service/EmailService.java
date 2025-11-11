package com.example.Online.Quiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendQuizResultEmail(String recipient, String quizTitle, int score, int total) {
        new Thread(() -> {
            try {
                System.out.println("📧 Sending quiz result to: " + recipient);
                
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("vasanth2k19@gmail.com"); // ⬅️ CRITICAL
                message.setTo(recipient);
                message.setSubject("Your Quiz Results for " + quizTitle);
                message.setText(
                    "Hey there!\n\n"
                    + "You completed the quiz: " + quizTitle + "\n"
                    + "Your score: " + score + "/" + total + "\n\n"
                    + "Great job!\n\n"
                    + "- Online Quiz App"
                );
                
                mailSender.send(message);
                System.out.println("✅ Quiz result email sent to " + recipient);
            } catch (Exception e) {
                System.err.println("❌ Email failed: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}
