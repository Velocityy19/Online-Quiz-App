package com.example.Online.Quiz.Service;

import com.example.Online.Quiz.Models.Questions;
import com.example.Online.Quiz.Repository.QuestionsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class QuestionsServiceTest {

    @Mock
    private QuestionsRepository questionsRepository;

    @InjectMocks
    private QuestionsService questionsService;

    @Test
    void testGetAllQuestions() {
        when(questionsRepository.findAll()).thenReturn(Arrays.asList(new Questions(), new Questions()));
        List<Questions> list = questionsService.getAllQuestions();
        assertEquals(2, list.size());
    }

    @Test
    void testSaveQuestion() {
        Questions q = new Questions();
        when(questionsRepository.save(q)).thenReturn(q);
        Questions saved = questionsService.saveQuestion(q);
        assertNotNull(saved);
    }
}
