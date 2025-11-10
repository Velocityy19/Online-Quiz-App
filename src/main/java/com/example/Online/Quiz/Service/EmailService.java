package com.example.Online.Quiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Method for sending Email 
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

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
