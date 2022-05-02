package com.example.controller;

import java.util.ArrayList;

import com.example.model.Chami;
import com.example.service.ChamiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

//import com.google.api.services.storage.Storage.BucketAccessControls.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController                 //type de controleur
@CrossOrigin                    //accept toutes les connexions
@RequestMapping("/api/chamis")  //suite de l'url après l'adresse du serveur
public class ChamiController {
        
    @Autowired                  //obligatoire pour spring
    private ChamiService chamiService;

<<<<<<< HEAD
    @GetMapping("/")            //suite url pour GET
    /*cette fonction traite deux type d'URL différent : 
            1:  en finissant l'url avec un / 
                        => la fonction retourne tous les utilisateurs
            2:  en finissant l'url avec /?emai=...
                        => en remplacant ... par l'email d'un chami
                        => retourne le chami s'il existe avec cet email
    */
    public List<Chami> allUsers(@RequestParam(required = false) String email) { 
        List<Chami> chamiList = new ArrayList<Chami>() {
            
        };
        if(email == null){
            chamiList = chamiService.getAllChami();
=======
    @GetMapping("/")
    // public List<Chami> allUsers(@RequestHeader("Authorization") String jwt) {
    public List<Chami>allUsers(@RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Chami> listChami= chamiService.getAllChami();
            return listChami;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
>>>>>>> masterBackup
        }
    }
    // public List<Chami> allUsers(@RequestHeader("Authorization") String jwt) 
        
        /*if(chamiList.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
        }*/

    

    @GetMapping("/mail") //extraire le mail du token
    public List<Chami> readByMail(@RequestParam("email") String email, @RequestHeader("Authorization") String jwt) {
        List<Chami> chamiList = new ArrayList<Chami>();
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Chami> chami = chamiService.getByEmail(email);
            if(chami.isPresent()){
                chamiList.add(chami.get());
            }
          } catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
          }

        

        if(chamiList.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
        }

        return chamiList;
    }

<<<<<<< HEAD
    

    @GetMapping("/{userId}") //finir l'url par /un_login_de_chami retourne le chami à partir son login s'il existe
    public Chami read(@PathVariable(value="userId") String id){
        Optional<Chami> chami = chamiService.getChami(id);
        if(!chami.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
=======
    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value="userId") String id, @RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Chami> chami = chamiService.getChami(id);
            if(!chami.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
            }
            return chami.get();
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
>>>>>>> masterBackup
        }
    }

<<<<<<< HEAD
    @PostMapping("/{userId}")   //finir l'url avec /un_login_de_chami créer un chami de login un_login_de_chami
                                //pour créer le chami, il faut récupérer les données du body
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami chami) {
        if(id.equals(chami.getLogin())){
            return chamiService.saveChami(chami);
=======
    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami chami,@RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id.equals(chami.getLogin())){
                return chamiService.saveChami(chami);
            }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
            }
>>>>>>> masterBackup
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

<<<<<<< HEAD
    @PutMapping("/{userId}")//modifie le chami
    public Chami update(@PathVariable(value="userId") String id, @RequestBody Chami chami){
        if(id.equals(chami.getLogin())){
            Optional<Chami> chamiOpt = chamiService.getChami(id);
            if(!chamiOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le chami n'existe pas");
            }
            //Chami chamiToUpdate = chamiOpt.get();
            chamiService.deleteChami(id);
            return chamiService.saveChami(chami);
            }
        else{
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
        }
    }

    @DeleteMapping("/{userId}")//supprime le chami
    public void delete(@PathVariable(value="userId") String id){
        Optional<Chami> chamiOpt = chamiService.getChami(id);
        if(!chamiOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
=======
    @PutMapping("/{userId}")
    public Chami update(@PathVariable(value="userId") String id, @RequestBody Chami chami,@RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id.equals(chami.getLogin())){
                Optional<Chami> chamiOpt = chamiService.getChami(id);
                if(!chamiOpt.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le chami n'existe pas");
                }
                chamiOpt.get();
                chamiService.deleteChami(id);
                return chamiService.saveChami(chami);
                }
            else{
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
            }
        }
        catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value="userId") String id,@RequestHeader("Authorization") String jwt){
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Chami> chamiOpt = chamiService.getChami(id);
            if(!chamiOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
            }
            chamiService.deleteChami(id);
        } catch(FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
>>>>>>> masterBackup
        }
    }
    }



