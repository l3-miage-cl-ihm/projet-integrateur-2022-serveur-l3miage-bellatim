package com.example.service;


import java.util.Optional;

import com.example.model.Defi;
import com.example.repository.ChamiRepository;
import com.example.repository.DefiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.Data;

@Data
@Service
public class DefisRepository {
    
    @Autowired
    private DefiRepository defiRepository;

    public Optional<Defi> getDefi(final int id) {
        return defiRepository.findById(id);
    }

    public List<Defi> getAllDefi() {
        return defiRepository.findAll();
    }

    public void deleteDefi(final int id) {
        defiRepository.deleteById(id);
    }

    public Defi saveDefi(Defi defi){
        return defiRepository.save(defi);
    }

}
