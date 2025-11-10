package com.example.Online.Quiz.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Service.QuizService;

import jakarta.servlet.http.HttpSession;



@Controller
public class QuizController {
    
    @Autowired
    QuizService quizService;

    // View available quizzes to the participant
    @GetMapping("/quizzes")
    public String viewQuizzes(HttpSession session , Model model) {
        if(session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        else{
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "participant_quizzes";
        }
    }

    // View quiz with ID
    @GetMapping("/quiz/{id}")
    public String startQuiz(@PathVariable Long id,Model model) {
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestions());
        return "quiz-page";

    }
    

}