package com.example.Online.Quiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Send quiz result email
    public void sendQuizResultEmail(String to, String quizTitle, int score, int totalQuestions) {
        String subject = "Quiz Results - " + quizTitle;
        String body = String.format(
            "Hello!\n\n" +
            "You’ve completed the quiz: %s\n" +
            "Your Score: %d / %d\n\n" +
            "Keep practicing and good luck next time!\n\n" +
            "Best,\nOnline Quiz App Team",
            quizTitle, score, totalQuestions
        );
        sendEmail(to, subject, body);
    }

    // Send registration email
    public void sendRegistrationEmail(String to, String userName) {
        String subject = "Welcome to Online Quiz App 🎉";
        String body = "Hello " + userName + ",\n\n" +
                      "Welcome to the Online Quiz App! Your account has been successfully created.\n\n" +
                      "You can now log in and start exploring quizzes.\n\n" +
                      "Visit: https://online-quiz-app-6yo4.onrender.com/login\n\n" +
                      "Happy Learning!\n\n" +
                      "– The Online Quiz Team";
        sendEmail(to, subject, body);
    }

    // Core method
    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }
}
