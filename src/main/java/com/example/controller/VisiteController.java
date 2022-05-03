package com.example.controller;

import com.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.service.ChamiService;
import com.example.service.DefiService;
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
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Visite> listVisite = service.getAllVisite();
            return listVisite;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    @GetMapping("/{idVisite}")
    public Visite read(@PathVariable(value = "idVisite") int id, @RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Visite> visite = service.getVisite(id);
            if(!visite.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");
            }
            return visite.get();
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @PostMapping(value="/{idVisite}")
    public Visite create(@PathVariable(value = "idVisite") int id, @RequestBody Visite visite, @RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id == visite.getId()){
                return service.saveVisite(visite);
            }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id entré en paramètre est celui de la visite sont différent");
            }
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }
    
    @PutMapping(value = "/{idVisite}")
    public Visite update(@PathVariable(value = "idVisite") int id, @RequestBody Visite visite, @RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id == visite.getId()){
                Optional<Visite> visiteTMP = service.getVisite(id);
                if(!visiteTMP.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite à modifier n'existe pas");
                }
                Visite visiteF = visiteTMP.get();
                visiteF.setJoueur(visite.getJoueurs());
                visiteF.setRang(visite.getRang());
                return service.saveVisite(visiteF);
                }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id passé en paramètre n'est pas le même que celui de la visite");
            }
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }
    
    @DeleteMapping("/{idVisite}")
    public void delete(@PathVariable(value = "idVisite") int id) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Visite> visiteOpt = service.getVisite(id);
            if(!visiteOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas");
            }
            service.deleteVisite(id);
        } catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }



    ////////////////TEST///////////////


    @GetMapping("/insert")
    public Visite testVisite(){


        Chami c1 = new Chami("toto", 12, "toto@gmail.com");
        Chami c2 = new Chami("joe", 12, "joe@gmail.com");
        Chami c3 = new Chami("francois", 10, "francois@gmail.com");
        Chami c4 = new Chami("david", 9, "david@gmail.com");

        List<Chami> joueurs = new ArrayList<>();
        joueurs.add(c1);
        joueurs.add(c2);
        joueurs.add(c3);
        joueurs.add(c4);

        Defi d = new Defi("D199", "fais du sport", null, c1, Categorie.SPORTIF, null);

        Etape e1  = new Etape(1, "il faut chercher l'indice 1", d);
        Etape e2  = new Etape(2, "il faut chercher l'indice 2", d);
        Etape e3  = new Etape(3, "il faut chercher l'indice 3", d);
        Etape e4  = new Etape(4, "il faut chercher l'indice 4", d);
        Etape e5  = new Etape(5, "il faut chercher l'indice 5", d);
        Media m1  = new Media(1, "regarde la photo 1", d, "maphoto.com", TypeMEDIA.PHOTO);

        ArrayList<Etape> etapes = new ArrayList<>();
        etapes.add(e1);
        etapes.add(e2);
        etapes.add(e3);
        etapes.add(e4);
        etapes.add(e5);
        etapes.add(m1);

        d.setEtape(etapes);

        Visite visite = new Visite(joueurs, d, 0);
        return service.saveVisite(visite);

    }
}
