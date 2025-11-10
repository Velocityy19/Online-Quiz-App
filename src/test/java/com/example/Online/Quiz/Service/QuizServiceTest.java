package com.example.Online.Quiz.Service;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    void testGetAllQuizzes() {
        when(quizRepository.findAll()).thenReturn(Arrays.asList(new Quiz(), new Quiz()));
        List<Quiz> quizzes = quizService.getAllQuizzes();
        assertEquals(2, quizzes.size());
    }

    @Test
    void testSaveQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("New Quiz");
        when(quizRepository.save(quiz)).thenReturn(quiz);
        Quiz saved = quizService.saveQuiz(quiz);
        assertEquals("New Quiz", saved.getTitle());
    }

    @Test
    void testGetQuizById() {
        Quiz quiz = new Quiz();
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
        assertNotNull(quizService.getQuizById(1L));
    }
}
