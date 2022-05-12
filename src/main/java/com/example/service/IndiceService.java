package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Indice;
import com.example.repository.IndiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndiceService {

    @Autowired
    private IndiceRepository indiceRepository;

    public Optional<Indice> getIndice(final int id){
        return indiceRepository.findById(id);
    }

    public List<Indice> getAllIndice(){
        return indiceRepository.findAll();
    }

    public void deleteIndice(final int id){
        indiceRepository.deleteById(id);
    }

    public Indice saveIndice(Indice media){
        return indiceRepository.save(media);
    }
}
