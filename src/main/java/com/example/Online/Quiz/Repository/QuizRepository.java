package com.example.Online.Quiz.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTitleContainingIgnoreCase(String title);
}
