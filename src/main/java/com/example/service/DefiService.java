package com.example.service;


import java.util.Optional;

import com.example.model.Defi;
import com.example.repository.DefiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefiService {
    
    @Autowired
    private DefiRepository defiRepository;

    public Optional<Defi> getDefi(final String id) {
        return defiRepository.findById(id);
    }

    public List<Defi> getAllDefis() {
        return defiRepository.findAll();
    }

    public void deleteDefi(final String id) {
        defiRepository.deleteById(id);
    }

    public Defi saveDefi(Defi defi){
        return defiRepository.save(defi);
    }

    public List<Defi> getDefisByChami(final String id){
        return defiRepository.findByAuteur(id);
    }


}
