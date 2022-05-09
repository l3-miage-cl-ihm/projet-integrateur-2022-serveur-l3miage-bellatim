package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.model.Visite;
import com.example.model.VisiteDTO;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Mapper {
    
    @Autowired
    private ChamiService cService;

    @Autowired
    private DefiService dService;

    public VisiteDTO toDTO(Visite visite) {
        try{
            int defiId = visite.getDefi().getId();
            String joueurId = visite.getJoueurs().get(0).getId();
            VisiteDTO visiteDTO = new VisiteDTO( joueurId, defiId,0);
            return visiteDTO;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Erreur");
        }
        
    }

    public Visite toVisite(VisiteDTO vTDO){
            Optional<Defi> defiOpt = dService.getDefi(vTDO.getDefi());
            Defi defi;
            if(defiOpt.isPresent()){
                defi=defiOpt.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Unauthorized");
            }

            Optional<Chami> chamiOpt = cService.getChami(vTDO.getJoueur());
            Chami chami;
            if(chamiOpt.isPresent()){
                chami=chamiOpt.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Unauthorized");
            }
            List<Chami> chamiList = new ArrayList<Chami>();
            chamiList.add(chami);
            Visite visite = new Visite (chamiList, defi, 1,LocalDateTime.now());
            return visite;
    }
}
