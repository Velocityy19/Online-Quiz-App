package com.example.Online.Quiz.Repository;

import com.example.Online.Quiz.Models.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Test
    void testSaveAndFindQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Algorithms");

        quizRepository.save(quiz);

        List<Quiz> allQuizzes = quizRepository.findAll();
        assertEquals(1, allQuizzes.size());
        assertEquals("Algorithms", allQuizzes.get(0).getTitle());
    }
}
