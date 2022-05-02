package com.example.controller;

import com.example.model.*;
import java.util.List;
import java.util.Optional;

import com.example.service.VisiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
@RequestMapping("api/visite")
public class VisiteController {
    
    @Autowired
    private VisiteService service;

    @GetMapping(value="/")
    public List<Visite> allVisites() {
        return service.getAllVisite();
    }

    @GetMapping("/{idVisite}")
    public Visite read(@PathVariable(value = "idVisite") int id){
        Optional<Visite> visite = service.getVisite(id);

        if(!visite.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");

        return visite.get();
    }

    @PostMapping(value="/{idVisite}")
    public Visite create(@PathVariable(value = "idVisite") int id, @RequestBody Visite visite) {
        if(id != visite.getId())
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id entré en paramètre est celui de la visite sont différent");
        
        return service.saveVisite(visite);
    }
    
    @PutMapping(value = "/{idVisite}")
    public Visite update(@PathVariable(value = "idVisite") int id, @RequestBody Visite visite){
        if(id != visite.getId())
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id passé en paramètre n'est pas le même que celui de la visite");
        
        Optional<Visite> visiteTMP = service.getVisite(id);

        if(!visiteTMP.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la visite à modifier n'existe pas");

        Visite visiteF = visiteTMP.get();
        visiteF.setJoueur(visite.getJoueurs());
        return service.saveVisite(visiteF);
    }
    
    @DeleteMapping("/{idVisite}")
    public void delete(@PathVariable(value = "idVisite") int id){
        service.deleteVisite(id);
    }
}
