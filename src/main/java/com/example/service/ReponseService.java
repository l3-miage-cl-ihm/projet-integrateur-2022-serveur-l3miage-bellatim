package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Reponse;
import com.example.repository.ReponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReponseService {
 
    @Autowired
    private ReponseRepository service;

    public Optional<Reponse> getReponse(final int id){
        return service.findById(id);
    }

    public List<Reponse> getAllReponse(){
        return service.findAll();
    }

    public void deleteReponse(final int id){
        service.deleteById(id);
    }

    public Reponse saveReponse(Reponse rep){
        return service.save(rep);
    }



}
