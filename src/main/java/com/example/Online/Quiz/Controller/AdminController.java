package com.example.Online.Quiz.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Online.Quiz.Models.Questions;
import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Models.Options;
import com.example.Online.Quiz.Service.QuestionsService;
import com.example.Online.Quiz.Service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionsService questionsService;
    
    // Admin Dashboard
    @GetMapping("/home")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    //View quizzes for admin
    @GetMapping("/quizzes")
    public String viewQuizzes(Model model) {
        List<Quiz> quizzes =  quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "admin_quizzes";
    }
    
    //Add Quiz 
    @GetMapping("/add-quiz")
    public String addQuiz(Model model) {
        model.addAttribute("quiz",new Quiz());
        return "add_quiz";
    }

    //Save Quiz
    @PostMapping("/add-quiz")
    public String saveQuiz(@ModelAttribute Quiz quiz) {
        quizService.saveQuiz(quiz);
        return "redirect:/admin/quizzes";
    }
       
    
    //Delete Quiz
    @GetMapping("/delete-quiz/{id}")
    public String deleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
           return "redirect:/admin/quizzes";
    }

    // Edit Quiz page
    @GetMapping("/quiz/edit/{id}")
    public String editQuiz(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        return "edit_quiz";
    }
    
    // Searching a quiz
    @GetMapping("/search-quiz")
    public String searchQuiz(@RequestParam("title") String title, Model model) {
        List<Quiz> quizzes = quizService.searchQuizByTitle(title);
        model.addAttribute("quizzes", quizzes);
        return "manage_questions";
    }

    // Save edited quiz
    @PostMapping("/quiz/update")
    public String updateQuiz(@ModelAttribute Quiz quiz) {
        quizService.saveQuiz(quiz); // reuse same save method
        return "redirect:/admin/quizzes";
    }


    // QUESTIONS MAPPING 
    // View All questions for a quiz 
    @GetMapping("/quiz/{quizId}/questions")
   public String viewAllQuestions(@PathVariable Long quizId, Model model) {
    Quiz quiz = quizService.getQuizById(quizId);
    model.addAttribute("quiz", quiz);
    model.addAttribute("questions", quiz.getQuestions());
    return "admin_questions";
}
    // Show form to add a new question
    @GetMapping("/quiz/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId, Model model) {
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", new Questions());
        return "add_question";
    }

    //Add a new question
    @PostMapping("/quiz/{quizId}/add-question")
public String saveQuestion(@PathVariable Long quizId,
                           @ModelAttribute Questions question,
                           @RequestParam("correctOption") int correctIndex) {

    Quiz quiz = quizService.getQuizById(quizId);
    question.setQuiz(quiz);

    // Link options + set correct answer
    if (question.getOptions() != null && correctIndex < question.getOptions().size()) {
        for (int i = 0; i < question.getOptions().size(); i++) {
            Options option = question.getOptions().get(i);
            option.setQuestion(question);
            option.setCorrect(i == correctIndex);
        }
    }

    questionsService.saveQuestion(question);

    // Redirect back to that quiz’s question list
    return "redirect:/admin/quiz/" + quizId + "/questions";
}


    // Delete question
    @GetMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionsService.deleteQuestion(id);
        return "redirect:/admin/quizzes";
    }

    // method to display questions
    @GetMapping("/manage-questions")
    public String manageQuestions(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "manage_questions";
    }
}
