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



@RestController
@CrossOrigin
@RequestMapping("/api/chamis")
public class ChamiController {
        
    @Autowired
    private ChamiService chamiService;

    @GetMapping("/")
    // public List<Chami> allUsers(@RequestHeader("Authorization") String jwt) {
    public List<Chami> allUsers(@RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Chami> listChami= chamiService.getAllChami();
            return listChami;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }
    // public Li
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
        }
    }

    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami chami, @RequestHeader("Authorization") String jwt) {
        try{
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(id.equals(chami.getLogin())){
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
        }
    }
    }



