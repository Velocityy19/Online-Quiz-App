package com.example.Online.Quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    
}
