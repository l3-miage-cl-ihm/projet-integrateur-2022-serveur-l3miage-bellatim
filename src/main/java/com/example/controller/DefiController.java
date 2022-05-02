package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.Defi;
import com.example.model.Indice;
import com.example.model.Media;
import com.example.model.Question;
import com.example.model.TypeMEDIA;
import com.example.service.ChamiService;
import com.example.service.DefiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/defis")
public class DefiController {

    @Autowired
    private DefiService defiService;

    // 404 si pas de slash
    @GetMapping("/")
    public List<Defi> allDefis(@RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Defi> lesDefis = defiService.getAllDefis();
            return lesDefis;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }

        /* XXX Pourquoi lever une exception ???
        if (lesDefis.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'y a pas de défis.");
        }*/
//il est écrit dans l'énoncé de lever une erreur
// OK mais alors une erreur au sens HTTP...
// Vérifiez que vous arrivez à peupler la base avec des requêtes
//OK
    }

    @GetMapping("/chami/{userId}")
    public List<Defi> defisByChami(@PathVariable(value="userId") String id, @RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Defi> lesDefis = defiService.getDefisByChami(id);
            return lesDefis;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    @Transactional(readOnly = true)
    @GetMapping("/{defiId}")
    public Defi read(@PathVariable(value = "defiId") String id,@RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Defi> defi = defiService.getDefi(id);
            if (defi.isPresent()) {
                return defi.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No defi found");
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    // XXX => à tester
    @PostMapping("/{defiId}")
    public Defi create(@PathVariable(value = "defiId") String id, @RequestBody Defi defi,@RequestHeader("Authorization") String jwt) {
        if(!(defi.getId().equals(id))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong id");
        }
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return defiService.saveDefi(defi);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
        return defiService.saveDefi(defi);
    }

    @Autowired
    ChamiService chamiService;

    @PutMapping("/{defiId}")
    public Defi update(@PathVariable(value = "defiId") String id, @RequestBody Defi defi) {
        
        if(!(defi.getId().equals(id))) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "L'id du défi passé en paramètre n'est pas le même que celui saisi.");
        }
        
        Optional<Defi> leDefiOpt = defiService.getDefi(id);
        
        if (!leDefiOpt.isPresent()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le défi n'existe pas.");
        }

        //if(leDefiOpt.get().getAuteur().getLogin().equals(defi.getAuteur().getLogin())){
            Defi leDefis = leDefiOpt.get();
            leDefis.setEtape(defi.getEtape());
            leDefis.setTitre(defi.getTitre());
            return defiService.saveDefi(leDefis);
            //defiService.deleteDefi(id);
            //return defiService.saveDefi(defi);
        /*}
        else{
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"l'auteur est différent ");
        }*/
        // return leDefi.get();
    }

    @DeleteMapping("/{defiId}")
    public void delete(@PathVariable(value = "defiId") String id,@RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Defi> defiopt = defiService.getDefi(id);
            if(!defiopt.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le défi n'existe pas.");
            }
            else{
                defiService.deleteDefi(id);
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
        
    }
}
