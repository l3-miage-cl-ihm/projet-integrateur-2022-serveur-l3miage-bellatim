
package com.example.service;

import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Visite;
import com.example.repository.ChamiRepository;
import com.example.repository.VisiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChamiService {
    

    @Autowired
    private ChamiRepository chamiRepository;

    public Optional<Chami> getChami(final String login) {
        return chamiRepository.findById(login);
    }

    public Optional<Chami> getByEmail(String email){
        return chamiRepository.findByEmail(email);
    }

    public List<Chami> getAllChami() {
        return chamiRepository.findAll();
    }

    public void deleteChami(final String login) {
        chamiRepository.deleteById(login);
    }

    public Chami saveChami(Chami chami){
        return chamiRepository.save(chami);
    }

    @Autowired
    private VisiteRepository visiteRepository;

    public List<Chami> getAllChamisByVisite(int visiteId){
        Optional<Visite> visiteOpt = visiteRepository.findById(visiteId);
        if(visiteOpt.isPresent()){
            return chamiRepository.findByVisitesId(visiteOpt.get().getId());
        }
        else{
            return null;
        }
    }
    
}
