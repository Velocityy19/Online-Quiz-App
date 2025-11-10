package com.example.Online.Quiz.Repository;

import com.example.Online.Quiz.Models.Quiz;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class QuizRepositoryTest {

    @Mock
    private QuizRepository quizRepository;

    @Test
    void testFindByTitleContainingIgnoreCase() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Java Test");

        when(quizRepository.findByTitleContainingIgnoreCase("java"))
                .thenReturn(Arrays.asList(quiz));

        List<Quiz> results = quizRepository.findByTitleContainingIgnoreCase("java");

        assertEquals(1, results.size());
        assertEquals("Java Test", results.get(0).getTitle());
    }
}
