package com.example.Online.Quiz.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answers {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Generation of ID
    private Long id;

    @ManyToOne
    private QuizAttempt attempt;

    @ManyToOne
    private Questions question;

    @ManyToOne
    private Options selectedOption;

    private boolean isCorrect;
}
