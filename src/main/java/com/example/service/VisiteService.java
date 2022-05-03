package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Visite;
import com.example.repository.VisiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisiteService {
    
    @Autowired
    private VisiteRepository visiteRepository;

    public Optional<Visite> getVisite(final int id){
        return visiteRepository.findById(id);
    }

    public List<Visite> getAllVisite(){
        return visiteRepository.findAll();
    }

    public void deleteVisite(int visite){
        visiteRepository.deleteById(visite);
    }

    public Visite saveVisite(Visite visite){
        return visiteRepository.save(visite);
    }
}
