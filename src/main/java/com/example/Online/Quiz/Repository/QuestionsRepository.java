package com.example.Online.Quiz.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByQuiz_QuizId(Long quizId);
}
