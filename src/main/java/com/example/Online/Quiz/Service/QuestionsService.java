package com.example.Online.Quiz.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online.Quiz.Models.Options;
import com.example.Online.Quiz.Models.Questions;
import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Repository.QuestionsRepository;
import com.example.Online.Quiz.Repository.QuizRepository;

@Service
public class QuestionsService {
   
    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired 
    QuizRepository quizRepository;

    // Add new questions to quiz
    public Questions addQuestions(Long quizId, Questions questions){
         Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(quizOptional.isPresent()){
            Quiz quiz = quizOptional.get();
            questions.setQuiz(quiz);
            return questionsRepository.save(questions);
        }
        else {
            throw new RuntimeException("Quiz with ID " + quizId + " not found");
        }
    }

    //Get all Questions for a specific quiz
   public List<Questions> getQuestionsByQuizId(Long quizId) {
        return questionsRepository.findByQuiz_QuizId(quizId);
    }

    //Get a single QuestionById
    public Questions getQuestionById(Long questionId) {
        return questionsRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question with ID " + questionId + " not found"));
    }

    //Update a Question
    public Questions updateQuestion(Long questionId, Questions updatedQuestion) {
    Questions existingQuestion = questionsRepository.findById(questionId)
        .orElseThrow(() -> new RuntimeException("Question with ID " + questionId + " not found"));

    existingQuestion.setText(updatedQuestion.getText());
    existingQuestion.setPoints(updatedQuestion.getPoints());

    // Clear old options and replace with new ones
    existingQuestion.getOptions().clear();
    if (updatedQuestion.getOptions() != null) {
        for (Options option : updatedQuestion.getOptions()) {
            option.setQuestion(existingQuestion); // maintain relationship
            existingQuestion.getOptions().add(option);
        }
    }

    return questionsRepository.save(existingQuestion);
}
    //Delete a question
    public void deleteQuestion(Long questionId){
        questionsRepository.deleteById(questionId);
    }

    //Get all questions
    public List<Questions> getAllQuestions(){
        return questionsRepository.findAll();
    }

    // Save a question
    public void saveQuestion(Questions question) {
        if (question.getOptions() != null) {
            question.getOptions().forEach(opt -> opt.setQuestion(question));
        }
        questionsRepository.save(question);
    }
}


