package com.example.Online.Quiz.Controller;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private QuizController quizController;

    @Test
    void testViewQuizzes_UserLoggedIn() {
        // Arrange
        Quiz quiz = new Quiz();
        quiz.setTitle("Database Quiz");
        when(session.getAttribute("user")).thenReturn("testUser");
        when(quizService.getAllQuizzes()).thenReturn(List.of(quiz));

        // Act
        String viewName = quizController.viewQuizzes(session, model);

        // Assert
        verify(quizService, times(1)).getAllQuizzes();
        verify(model, times(1)).addAttribute("quizzes", List.of(quiz));
        assertEquals("participant_quizzes", viewName);
    }

    @Test
    void testViewQuizzes_UserNotLoggedIn() {
        // Arrange
        when(session.getAttribute("user")).thenReturn(null);

        // Act
        String viewName = quizController.viewQuizzes(session, model);

        // Assert
        verify(quizService, never()).getAllQuizzes();
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("redirect:/login", viewName);
    }
}
