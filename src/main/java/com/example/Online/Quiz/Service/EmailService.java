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
            System.out.println("=================================");
            System.out.println("📧 Preparing to send email...");
            System.out.println("To: " + recipient);
            System.out.println("Quiz: " + quizTitle);
            System.out.println("Score: " + score + "/" + total);
            System.out.println("=================================");
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("vasanth2k19@gmail.com"); // ⬅️ IMPORTANT: Add this
            message.setTo(recipient);
            message.setSubject("Your Quiz Results for " + quizTitle);
            message.setText(
                "Hey there!\n\n"
                + "You completed the quiz: " + quizTitle + "\n"
                + "Your score: " + score + "/" + total + "\n\n"
                + "Great job!\n\n"
                + "- Online Quiz App"
            );
            
            System.out.println("📤 Sending email...");
            mailSender.send(message);
            System.out.println("✅ Email sent successfully to " + recipient);
            System.out.println("=================================");
            
        } catch (Exception e) {
            System.err.println("=================================");
            System.err.println("❌ EMAIL SENDING FAILED!");
            System.err.println("Recipient: " + recipient);
            System.err.println("Error Type: " + e.getClass().getName());
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("=================================");
            e.printStackTrace();
        }
    }
}
