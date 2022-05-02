package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.service.ChamiService;

//import com.google.api.services.storage.Storage.BucketAccessControls.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;


@RestController                 //type de controleur
@CrossOrigin                    //accept toutes les connexions
@RequestMapping("/api/chamis")  //suite de l'url après l'adresse du serveur
public class ChamiController {
        
    @Autowired                  //obligatoire pour spring
    private ChamiService chamiService;

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
        }
        else{
            Optional<Chami> chami = chamiService.getByEmail(email);
            if(chami.isPresent()){
                chamiList.add(chami.get());
            }
        }
        
        /*if(chamiList.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
        }*/

        return chamiList;
    }

    

    @GetMapping("/{userId}") //finir l'url par /un_login_de_chami retourne le chami à partir son login s'il existe
    public Chami read(@PathVariable(value="userId") String id){
        Optional<Chami> chami = chamiService.getChami(id);
        if(!chami.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
        }
        return chami.get();
    }

    @PostMapping("/{userId}")   //finir l'url avec /un_login_de_chami créer un chami de login un_login_de_chami
                                //pour créer le chami, il faut récupérer les données du body
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami chami) {
        if(id.equals(chami.getLogin())){
            return chamiService.saveChami(chami);
        }
        else{
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
        }
    }

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
        }
        chamiService.deleteChami(id);
    }

    @GetMapping("/test")
    public Chami test() {
        Chami chami = new Chami("monLog", 12, "mail@gmail.com");
        return chamiService.saveChami(chami);
    }
}



