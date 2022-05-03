package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.Indice;
import com.example.model.Question;
import com.example.service.IndiceService;
import com.example.service.MediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin
@RequestMapping(value="/api/indice")
public class IndiceControleur {

    @Autowired
    private IndiceService indiceService;

    @GetMapping("/")
<<<<<<< HEAD
    public List<Indice> allItems(){
        return indiceService.getAllIndice();
    }

    @GetMapping("/{indiceID}")
    public Indice read(@PathVariable(value = "indiceID") int id){
        Optional<Indice> indice = indiceService.getIndice(id);

        if(!indice.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun indice n'existe à cet id");
        }

        return indice.get();
    }

    @PostMapping("/create")
    public Indice create(int id, @RequestBody Indice indice){
        return indiceService.saveIndice(indice);
=======
    public List<Indice> allItems(@RequestHeader("Authorization") String jwt){
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Indice> listIndice= indiceService.getAllIndice();
            return listIndice;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    @GetMapping("/{indiceID}")
    public Indice read(@PathVariable(value = "indiceID") int id, @RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Indice> indice = indiceService.getIndice(id);
            if(!indice.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun indice n'existe pour cet id");
            }
            return indice.get();
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @PostMapping("/create")
    public Indice create(@RequestBody Indice indice, @RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return indiceService.saveIndice(indice);
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    }

    @PutMapping(value="/{indiceID}")
    public Indice update(@PathVariable(value = "indiceID") int id, @RequestBody Indice indice) {
<<<<<<< HEAD
        
        if(id != indice.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "l'id de l'indice passé en paramètre est différent de celui saisi.");
        }

        Optional<Indice> indiceTMP = indiceService.getIndice(id);

        if(!indiceTMP.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun indice n'existe à cet id");
        }

        Indice newIndice = indiceTMP.get();
        newIndice.setRang(indice.getRang());
        newIndice.setLabel(indice.getLabel());

        return indiceService.saveIndice(newIndice);
=======
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id == indice.getId()){
                Optional<Indice> indiceTMP = indiceService.getIndice(id);
                if(!indiceTMP.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun indice n'existe à cet id");
                }
                Indice newIndice = indiceTMP.get();
                newIndice.setRang(indice.getRang());
                newIndice.setLabel(indice.getLabel());
                indiceService.deleteIndice(id);
                return indiceService.saveIndice(indice);
                }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id de l'indice passé en paramètre est différent de celui saisi.");
            }
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
    }

    @DeleteMapping("/{indiceID}")
    public void delete(@PathVariable(value = "indiceID") int id){
<<<<<<< HEAD
        indiceService.deleteIndice(id);
    }
    
}
=======
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Indice> indiceOpt = indiceService.getIndice(id);
            if(!indiceOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'indice n'existe pas");
            }
            indiceService.deleteIndice(id);
        } catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }
}
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
