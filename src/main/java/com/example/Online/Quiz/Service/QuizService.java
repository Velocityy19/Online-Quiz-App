package com.example.Online.Quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online.Quiz.Models.Quiz;
import com.example.Online.Quiz.Repository.QuizRepository;

@Service
public class QuizService {
    
    @Autowired
    QuizRepository quizRepository;

    // Get all quizzes
    public List<Quiz> getAllQuizzes(){
       return quizRepository.findAll();
    }

    // Get Quiz by ID
    public Quiz getQuizById(Long quizId){
        return quizRepository.findById(quizId).orElse(null);
    }

    //Save Quiz 
    public void saveQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }

    //Delete a quiz
    public void deleteQuiz(Long Id){
        quizRepository.deleteById(Id);
    }

    //search quiz by title
    public List<Quiz> searchQuizByTitle(String title) {
    return quizRepository.findByTitleContainingIgnoreCase(title);
}

}
