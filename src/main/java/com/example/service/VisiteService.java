package com.example.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.model.Visite;
import com.example.model.VisiteDTO;
import com.example.repository.ChamiRepository;
import com.example.repository.DefiRepository;
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

    // @Autowired
    // private Mapper mapper;

    public Optional<Visite> getVisite(final int id) {
        return visiteRepository.findById(id);
    }

    // public VisiteDTO getVisiteDTO(final int id){
    //     Optional<Visite> visiteOpt = visiteRepository.findById(id);
    //     Visite visite;
    //     if(!visiteOpt.isPresent()){
    //          throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "defi inexistant");
    //     }
    //     visite = visiteOpt.get();
    //     return mapper.toDTO(visite);
    // }

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

    @Autowired
    private DefiRepository defiRepository;

    public List<Visite> getAllVisitesByDefiId(int id) {
        Optional<Defi> defiOpt = defiRepository.findById(id);
        if (defiOpt.isPresent()) {
            return visiteRepository.findByDefiId(defiOpt.get().getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visites not found");
        }
    }

    // public List<VisiteDTO> getAllVisitesDTOByChamiId(String chamiId){
    //     Optional<Chami> chamiOpt = chamiService.getChami(chamiId);
    //     if(!chamiOpt.isPresent()){
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chami not found");
    //     }
    //     Chami chami = chamiOpt.get();
    //     List<Visite> visites = visiteRepository.findByJoueursId(chami.getId());
    //     List<VisiteDTO> visitesDTO = new ArrayList<VisiteDTO>();
    //     for(Visite visite : visites){
    //         visitesDTO.add(mapper.toDTO(visite));
    //     }
    //     return visitesDTO;
    // }

    public Visite finir(final int id){
        Optional<Visite> visiteOpt = visiteRepository.findById(id);
        if(!visiteOpt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite visite = visiteOpt.get();
        visite.setDateFin(LocalDateTime.now());
        return visiteRepository.save(visite);
    }
}
