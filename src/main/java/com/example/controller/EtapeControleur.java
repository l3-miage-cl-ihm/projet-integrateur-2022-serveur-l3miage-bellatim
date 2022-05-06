package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.Etape;
import com.example.service.EtapeService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

//ResponseEntity
@RestController
@CrossOrigin
@RequestMapping(value = "/api/etape")
public class EtapeControleur {

    @Autowired
    private EtapeService etapeService;

    @GetMapping("/")
    public List<Etape> allItems(@RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return etapeService.getAllItem();
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    /*@GetMapping("/defi/{defiId}")
    public List<Etape> allItemsByDefi(@PathVariable("defiId") int defiId,
            @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return etapeService.getAllItemByDefi(defiId);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }*/

    @GetMapping("/{itemId}")
    public Etape read(@PathVariable(value = "itemId") int id, @RequestHeader("Authorization") String jwt) {

        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Etape> etapeOpt = etapeService.getItem(id);
            if (!etapeOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item found");
            }
            return etapeOpt.get();
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }

    }

    @PostMapping("/create")
    public Etape create(@RequestBody Etape etape, @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return etapeService.saveItem(etape);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @PutMapping(value = "/{idEtape}")
    public Etape update(@PathVariable(value = "idEtape") int id, @RequestBody Etape etape,
            @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if (id != etape.getId()) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        "l'id de l'étape passé en paramètre est différent de celui saisi.");
            }

            Optional<Etape> etapeTMP = etapeService.getItem(id);
            if (!etapeTMP.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun item de description n'existe à cet id");
            }
            return etapeService.saveItem(etape);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    @DeleteMapping("/{idEtape}")
    public void delete(@PathVariable(value = "idEtape") int id, @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            etapeService.deleteItem(id);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

}
