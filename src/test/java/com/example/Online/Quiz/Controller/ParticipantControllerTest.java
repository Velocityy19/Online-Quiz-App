package com.example.Online.Quiz.Controller;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Service.EmailService;
import com.example.Online.Quiz.Service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipantControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private EmailService emailService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ParticipantController participantController;

    @Test
    void testViewAvailableQuizzes() {
        // Arrange
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz());

        // Make session return a Users instance so the controller's cast succeeds
        Users user = new Users();
        user.setEmail("someone@example.com");
        when(session.getAttribute("user")).thenReturn(user);

        when(quizService.getAllQuizzes()).thenReturn(quizList);
        String result = participantController.viewAvailableQuizzes(null, model, session);
        assertEquals("participant_quizzes", result);
        verify(model, times(1)).addAttribute(eq("quizzes"), eq(quizList));
    }
}
