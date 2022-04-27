package com.example.service;

import java.util.Optional;

import com.example.model.Chami;
import com.example.repository.ChamiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.Data;

@Data
@Service
public class ChamiService {
    

    @Autowired
    private ChamiRepository chamiRepository;

    public Optional<Chami> getChami(final String login) {
        return chamiRepository.findById(login);
    }

    public List<Chami> getAllChami() {
        return chamiRepository.findAll();
    }

    public void deleteEmployee(final String login) {
        chamiRepository.deleteById(login);
    }

    public Chami saveChami(Chami chami){
        return chamiRepository.save(chami);
    }

    
}
