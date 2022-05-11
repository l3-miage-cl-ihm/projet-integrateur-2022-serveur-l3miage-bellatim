package com.example.service;

import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.model.Visite;
import com.example.repository.ChamiRepository;
import com.example.repository.DefiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefiService {

    @Autowired
    private DefiRepository defiRepository;

    public Optional<Defi> getDefi(final int id) {
        return defiRepository.findById(id);
    }

    public List<Defi> getAllDefis() {
        return defiRepository.findAll();
    }

    public void deleteDefi(final int id) {
        defiRepository.deleteById(id);
    }

    public Defi saveDefi(Defi defi) {
        return defiRepository.save(defi);
    }

    @Autowired
    ChamiRepository chamiRepository;

    public List<Defi> getDefisByChami(final String id) {
        Optional<Chami> chami = chamiRepository.findById(id);
        if (chami.isPresent()) {
            return defiRepository.findByAuteur(chami.get());
        } else {
            return null;
        }
    }

    public Optional<Defi> getDefiByTitre(String titre){
        return defiRepository.findByTitre(titre);
    }

    @Autowired
    VisiteService visiteService;

    public void removeVisitesByDefi(final int id) {
        List<Visite> visites = visiteService.getAllVisitesByDefiId(id);
        for (Visite visite : visites) {
            visiteService.deleteVisite(visite.getId());
        }
        // defiRepository.deleteById(id);
    }

}
