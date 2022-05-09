package com.example.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Visite;
import com.example.repository.ChamiRepository;
import com.example.repository.VisiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VisiteService {

    @Autowired
    private VisiteRepository visiteRepository;

    @Autowired
    private SSEservice sseService;

    public Optional<Visite> getVisite(final int id) {
        return visiteRepository.findById(id);
    }

    public List<Visite> getAllVisite() {
        return visiteRepository.findAll();
    }

    public void deleteVisite(int visite) {
        visiteRepository.deleteById(visite);
    }

    public Visite saveVisite(Visite visite) throws IOException{
        sseService.doNotify();
        return visiteRepository.save(visite);
    }



    @Autowired
    private ChamiService chamiService;

    public List<Visite> getAllVisitesByChamiId(String chamiId) {
        Optional<Chami> chamiOpt = chamiService.getChami(chamiId);
        if (chamiOpt.isPresent()) {
            return visiteRepository.findByJoueursId(chamiOpt.get().getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visites not found");
        }
    }
}
