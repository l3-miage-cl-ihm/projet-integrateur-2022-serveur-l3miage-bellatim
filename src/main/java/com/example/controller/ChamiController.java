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
import org.springframework.web.bind.annotation.ResponseBody;
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
    public List<Chami> allUsers() {
        List<Chami> chamiList = chamiService.getAllChami();

        /*if(chamiList.size()==0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
        }*/

        return chamiList;
    }

    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value="userId") String id){
        Optional<Chami> chami = chamiService.getChami(id);
        if(!chami.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
        }
        return chami.get();
    }

    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value="userId") String id, @RequestBody Chami chami) {
        if(id.equals(chami.getLogin())){
            return chamiService.saveChami(chami);
        }
        else{
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
        }
    }

    @PutMapping("/{userId}")
    public Chami update(@PathVariable(value="userId") String id, @RequestBody Chami chami){
        if(id.equals(chami.getLogin())){
            Optional<Chami> chamiOpt = chamiService.getChami(id);
            if(!chamiOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le chami n'existe pas");
            }
            Chami chamiToUpdate = chamiOpt.get();
            return chamiService.saveChami(chamiToUpdate);
            }
        else{
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
        }
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value="userId") String id){
        Optional<Chami> chamiOpt = chamiService.getChami(id);
        if(!chamiOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
        }
        chamiService.deleteChami(id);
    }

    }



