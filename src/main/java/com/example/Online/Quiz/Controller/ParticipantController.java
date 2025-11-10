package com.example.Online.Quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.Online.Quiz.Models.Options;
import com.example.Online.Quiz.Models.Questions;
import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Service.EmailService;
import com.example.Online.Quiz.Service.QuestionsService;
import com.example.Online.Quiz.Service.QuizService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ParticipantController {
    
    @Autowired
    QuizService quizService;

    @Autowired
    QuestionsService questionsService;

    @Autowired
    private EmailService emailService;
    
    //Displaying the home page of Participant
     @GetMapping("/participant/quizzes")
    public String viewAvailableQuizzes(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Quiz> quizzes;
        if (search != null && !search.isEmpty()) {
            quizzes = quizService.searchQuizByTitle(search);
        } else {
            quizzes = quizService.getAllQuizzes();
        }
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("search", search);
        return "participant_quizzes";
    }
    
    //Quiz Viewing 
    @GetMapping("/participant/quiz/{quizId}")
    public String takeQuiz(@PathVariable Long quizId, Model model) {
    Quiz quiz = quizService.getQuizById(quizId);
    List<Questions> questions = questionsService.getQuestionsByQuizId(quizId);
    
    model.addAttribute("quiz", quiz);
    model.addAttribute("questions", questions);
    
    return "quiz_take";
}

    // Start quiz button 
    @GetMapping("/start-quiz")
    public String startQuiz(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "redirect:/participant/quizzes";
}
    // Submit quiz 
    @PostMapping("/participant/quiz/{quizId}/submit")
    public String submitQuiz(@PathVariable Long quizId, HttpServletRequest request, Model model, HttpSession session) {
    List<Questions> questions = questionsService.getQuestionsByQuizId(quizId);
    int score = 0;

    for (Questions q : questions) {
        String selectedOptionIdStr = request.getParameter("question_" + q.getId());
        if (selectedOptionIdStr != null) {
            Long selectedOptionId = Long.parseLong(selectedOptionIdStr);
            for (Options opt : q.getOptions()) {
                if (opt.isCorrect() && opt.getId().equals(selectedOptionId)) {
                    score += q.getPoints();
                }
            }
        }
    }

     Quiz quiz = quizService.getQuizById(quizId);
    Users user = (Users) session.getAttribute("user");

    // Send email summary
    try {
        emailService.sendQuizResultEmail(user.getEmail(), quiz.getTitle(), score, questions.size());
    } catch (Exception e) {
        System.out.println("Email sending failed: " + e.getMessage());
    }

    model.addAttribute("score", score);
    model.addAttribute("total", questions.size());
    model.addAttribute("quiz", quizService.getQuizById(quizId));

    return "quiz_result";
}

    
}
