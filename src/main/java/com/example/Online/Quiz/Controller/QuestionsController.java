package com.example.Online.Quiz.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Online.Quiz.Models.Questions;
import com.example.Online.Quiz.Service.QuestionsService;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;


    // Add a Question
    @PostMapping("/add")
    public String addQuestion(@RequestBody Questions question) {
        questionsService.saveQuestion(question);
        return "Question added successfully";
    }

    // Get all questions for a specific quiz
    @GetMapping("/quiz/{quizId}")
    public List<Questions> getQuestionsByQuiz(@PathVariable Long quizId) {
        return questionsService.getQuestionsByQuizId(quizId);
    }

    // Delete a question by ID
    @DeleteMapping("/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId) {
        questionsService.deleteQuestion(questionId);
        return "Question deleted successfully";
    }

    // Get all questions 
    @GetMapping("/all")
    public List<Questions> getAllQuestions() {
        return questionsService.getAllQuestions();
    }
}
