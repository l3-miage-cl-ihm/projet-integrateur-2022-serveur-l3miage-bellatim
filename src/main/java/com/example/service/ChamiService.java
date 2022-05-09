
package com.example.service;

import com.example.model.Chami;
import com.example.model.Visite;
import com.example.repository.ChamiRepository;
import com.example.repository.VisiteRepository;
import com.google.firebase.auth.FirebaseToken;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamiService {

    @Autowired
    private ChamiRepository chamiRepository;

    public Optional<Chami> getChami(final String id) {
        return chamiRepository.findById(id);
    }

    public Optional<Chami> getByEmail(String email) {
        return chamiRepository.findByEmail(email);
    }

    public List<Chami> getAllChami() {
        return chamiRepository.findAll();
    }

    public void deleteChami(final String login) {
        chamiRepository.deleteById(login);
    }

    public Chami saveChami(Chami chami) {
        return chamiRepository.save(chami);
    }

    @Autowired
    private VisiteRepository visiteRepository;

    public List<Chami> getAllChamisByVisite(int visiteId) {
        Optional<Visite> visiteOpt = visiteRepository.findById(visiteId);
        if (visiteOpt.isPresent()) {
            return chamiRepository.findByVisitesId(visiteOpt.get().getId());
        } else {
            return null;
        }
    }

    public boolean isAllowed(String login, FirebaseToken token) {
        Optional<Chami> chamiOpt = chamiRepository.findById(login);
        if (chamiOpt.isPresent()) {
            Chami chami = chamiOpt.get();
            if (chami.getEmail().equals(token.getEmail())) {
                return true;
            }
        }
        return false;
    }

    // public boolean uidVerification()

    // public boolean isAllowed(String id, FirebaseToken){
    // Optional<Chami> chamiOpt = chamiRepository.findById(id);
    // if(chamiOpt.isPresent()){
    // Chami chami = chamiOpt.get();
    // if(chami.getEmail().equals(token.getEmail())){
    // return true;
    // }
    // }
    // return false;
    // }

}
