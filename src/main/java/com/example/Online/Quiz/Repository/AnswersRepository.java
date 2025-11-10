package com.example.Online.Quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    
}
