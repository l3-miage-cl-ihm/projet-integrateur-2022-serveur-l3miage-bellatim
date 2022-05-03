package com.example.service;


import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.repository.ChamiRepository;
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

    @Autowired ChamiRepository chamiRepository;

    public List<Defi> getDefisByChami(final String id){
<<<<<<< HEAD
        return defiRepository.findByAuteur(id);
=======
        Optional<Chami> chami = chamiRepository.findById(id);
        if(chami.isPresent()){
            return defiRepository.findByAuteur(chami.get());
        }
        else{
            return null;
        }
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    }


}
