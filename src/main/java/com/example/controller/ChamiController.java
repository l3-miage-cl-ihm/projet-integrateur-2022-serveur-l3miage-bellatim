package com.example.controller;

import com.example.model.Chami;
import com.example.service.ChamiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//import com.google.api.services.storage.Storage.BucketAccessControls.List;

//verifications token uid
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
            List<Chami> listChami = chamiService.getAllChami();
            return listChami;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }
    // public List<Chami> allUsers(@RequestHeader("Authorization") String jwt)

    /*
     * if(chamiList.size()==0) {
     * throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
     * }
     */

    @GetMapping("/mail") // extraire le mail du token
    public List<Chami> readByMail(@RequestParam("email") String email, @RequestHeader("Authorization") String jwt) {
        List<Chami> chamiList = new ArrayList<Chami>();
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
            if (decodedToken.getEmail().equals(email)) {
                Optional<Chami> chami = chamiService.getByEmail(email);
                if (chami.isPresent()) {
                    chamiList.add(chami.get());
                }
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }

        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        if (chamiList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chami found");
        }

        return chamiList;
    }

    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value = "userId") String id, @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            // if(chamiService.isAllowed(id, token)){
            if (id.equals(token.getUid())) {
                Optional<Chami> chami = chamiService.getChami(id);
                if (!chami.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
                }
                return chami.get();
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @GetMapping("/{visiteId}")
    public List<Chami> allChamisByVisite(@PathVariable("visiteId") int visiteId,
            @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return chamiService.getAllChamisByVisite(visiteId);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value = "userId") String id, @RequestBody Chami chami,
            @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            // if(token.getUid().equals(uid)){
            // if(chamiService.is)
            if (id.equals(token.getUid())) { // verifier que l'id du token correspond a celui du chami
                Optional<Chami> chamiOpt = chamiService.getChami(id); // verifier que le chami n'existe pas deja
                if (chamiOpt.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Le chami existe déjà");
                }
                if (id.equals(chami.getId())) {
                    return chamiService.saveChami(chami);
                } else {
                    throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @PutMapping("/{userId}")
    public Chami update(@PathVariable(value = "userId") String id, @RequestBody Chami chami,
            @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            // if(chamiService.isAllowed(id,token)){
            if (id.equals(token.getUid())) {
                if (id.equals(chami.getLogin())) {
                    Optional<Chami> chamiOpt = chamiService.getChami(id);
                    if (!chamiOpt.isPresent()) {
                        throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le chami n'existe pas");
                    }
                    Chami updatedChami = chamiOpt.get();
                    updatedChami.setLogin(chami.getLogin());
                    updatedChami.setAge(chami.getAge());
                    // ajouter un id
                    // changer les attributs ?
                    // chamiService.deleteChami(id);
                    return chamiService.saveChami(chami);
                } else {
                    throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Le login ne correspond pas");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value = "userId") String id, @RequestHeader("Authorization") String jwt) {
        try {

            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            // if(chamiService.isAllowed(id,token)){
            if (id.equals(token.getUid())) {
                Optional<Chami> chamiOpt = chamiService.getChami(id);
                if (!chamiOpt.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas");
                }
                chamiService.deleteChami(id);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
            // Optional<Chami> chamiOpt = chamiService.getChami(id);
            // if(!chamiOpt.isPresent()) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe
            // pas");
            // }
            // chamiService.deleteChami(id);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }
}
