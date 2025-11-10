package com.example.Online.Quiz.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Service.QuizService;
import com.example.Online.Quiz.Service.QuestionsService;
import com.example.Online.Quiz.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionsService questionsService;

    @Mock
    private EmailService emailService;

    @Mock
    private Model model;

    @InjectMocks
    private ParticipantController participantController;

    @Test
    void testViewAvailableQuizzes() {
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz());
        when(quizService.getAllQuizzes()).thenReturn(quizList);

        String result = participantController.viewAvailableQuizzes(null, model);
        assertEquals("participant_quizzes", result);
    }
}
