package com.example.Online.Quiz.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    void testGetAllQuizzes() {
        // Arrange
        Quiz q1 = new Quiz();
        q1.setTitle("Java Basics");
        Quiz q2 = new Quiz();
        q2.setTitle("Spring Boot");

        when(quizRepository.findAll()).thenReturn(List.of(q1, q2));

        // Act
        List<Quiz> result = quizService.getAllQuizzes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
        verify(quizRepository).findAll();
    }
}
