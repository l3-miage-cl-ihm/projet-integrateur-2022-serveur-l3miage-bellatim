package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Question;
import com.example.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getQuestion(final int id){
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public void deleteQuestion(final int id){
        questionRepository.deleteById(id);
    }

    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
}
