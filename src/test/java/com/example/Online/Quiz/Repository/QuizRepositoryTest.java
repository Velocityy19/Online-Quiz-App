package com.example.Online.Quiz.Repository;

import com.example.Online.Quiz.Models.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Test
    void testFindByTitleContainingIgnoreCase() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Java Test");
        quizRepository.save(quiz);

        List<Quiz> results = quizRepository.findByTitleContainingIgnoreCase("java");
        assertFalse(results.isEmpty());
    }
}
