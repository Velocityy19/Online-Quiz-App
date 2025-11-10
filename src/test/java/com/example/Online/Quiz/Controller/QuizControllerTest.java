package com.example.Online.Quiz.Controller;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizService quizService;

    @Test
    void testGetAllQuizzes() throws Exception {
        Quiz quiz = new Quiz();
        quiz.setTitle("Database Quiz");

        when(quizService.getAllQuizzes()).thenReturn(List.of(quiz));

        mockMvc.perform(get("/participant/quizzes"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("quizzes"));
    }
}
