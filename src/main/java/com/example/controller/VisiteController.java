package com.example.controller;

import com.example.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.service.ChamiService;
import com.example.service.Mapper;
import com.example.service.VisiteService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private VisiteService visiteService;

    @Autowired 
    private Mapper mapper;

    
    @Autowired
    private ChamiService chamiService;

    
    /** 
     * Retourne toutes les visite 
     * @param jwt token d'authentification firebase
     * @return Liste des visites
     */
    @GetMapping("/")
    public List<Visite> allVisites(@RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            List<Visite> listVisite = visiteService.getAllVisite();
            return listVisite;
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    
    /** 
     * Retoure une visite donnée
     * @param id identifiant de la visite
     * @param jwt token d'authentification firebase
     * @return  
     */
    @GetMapping("/play/{idVisite}")
    public Visite read(@PathVariable(value = "idVisite") int id, @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Visite> visite = visiteService.getVisite(id);
            if (!visite.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");
            }
            return visite.get();
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    // @PostMapping("/")
    // public Visite create(@RequestBody Visite visite,
    //         @RequestHeader("Authorization") String jwt) {
    //     try {
    //         FirebaseAuth.getInstance().verifyIdToken(jwt);
    //         return visiteService.saveVisite(visite);
    //     } catch (FirebaseAuthException e) {
    //         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    //     }
    // }

    
    /** 
     * creer une nouvelle visite
     * @param visite data transfer object
     * @return visite créée
     */
    @PostMapping("/")
    public Visite create(@RequestBody VisiteDTO visiteDTO,
            @RequestHeader("Authorization") String jwt) throws IOException {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return visiteService.saveVisite(mapper.toVisite(visiteDTO));
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    
    /** 
     * c
     * @param createComplet
     * @return 
     */
    @PostMapping("/complet/")
    public Visite createComplet(@RequestBody Visite visite,
            @RequestHeader("Authorization") String jwt) throws IOException {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            return visiteService.saveVisite(visite);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    
    /** 
     * Met fin a une visite en ajoutant une date de fin
     * @param id
     * @param jwt
     * @return 
     * @throws IOException
     */
    @GetMapping("/fin/{idVisite}")
    public Visite finVisite(@PathVariable("idVisite") int id, @RequestHeader("Authorization") String jwt) throws IOException{
        try{
            //verifier joueur est dans liste des joueurs
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            String uid = token.getUid();
            boolean ok=false;
            List<Chami> listJoueur = visiteService.getVisite(id).get().getJoueurs();
            for (Chami chami : listJoueur) {
                if(chami.getId().equals(uid)){
                    ok=true;
                }
            }
            if(!ok){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
            Optional<Visite> visite = visiteService.getVisite(id);
            if (!visite.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");
            }
            // Visite visiteToEnd = visite.get();
            // visiteService.finir(id);
            // visiteToEnd.finir();
            return visiteService.finir(id);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    
    /** 
     * Ajouter une réponse a une visite
     * @param id identifiant de la visite
     * @param reponse reponse à ajouter
     * @param jwt token d'authentification
     * @return Visite modifiée
     * @throws IOException
     */
    @PutMapping("/{idVisite}/reponse")
    public Visite addReponse(@PathVariable("idVisite") int id, @RequestBody Reponse reponse,@RequestHeader("Authorization") String jwt) throws IOException{
        try {
            //verifier joueur est dans liste des joueurs
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            String uid = token.getUid();
            boolean ok=false;
            List<Chami> listJoueur = visiteService.getVisite(id).get().getJoueurs();
            for (Chami chami : listJoueur) {
                if(chami.getId().equals(uid)){
                    ok=true;
                }
            }
            if(!ok){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
            Optional<Visite> visite = visiteService.getVisite(id);
            if (!visite.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");
            }
            Visite visiteToUpdate = visite.get();
            visiteToUpdate.addReponse(reponse);
            return visiteService.saveVisite(visiteToUpdate);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }


    
    /** 
     * Ajoute un chami a une visite
     * @param id identifiant de la visite
     * @param idChami identifiant du joueur
     * @param reponse 
     * @param jwt token d'authentification
     * @return La visite modifiée
     * @throws IOException
     */
    @PutMapping("/{idVisite}/ajouterJoueur/{idChami}")
    public Visite addJoueur(@PathVariable("idVisite") int id,@PathVariable("idVisite") String idChami,@RequestHeader("Authorization") String jwt) throws IOException{
        try {
            //verifier joueur est dans liste des joueurs
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            String uid = token.getUid();
            boolean ok=false;
            List<Chami> listJoueur = visiteService.getVisite(id).get().getJoueurs();
            for (Chami chami : listJoueur) {
                if(chami.getId().equals(uid)){
                    ok=true;
                }
            }
            if(!ok){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
            Optional<Visite> visite = visiteService.getVisite(id);
            if (!visite.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas avec cet id");
            }
            Visite visiteToUpdate = visite.get();

            Optional<Chami> chamiOPT = chamiService.getChami(idChami);
            if(!chamiOPT.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le chami n'existe pas avec cet id");
            }
            visiteToUpdate.addChami(chamiOPT.get());
            return visiteService.saveVisite(visiteToUpdate);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    
    /** 
     * Retourn les visites d'un chami
     * @param "chamiId"
     * @return 
     */
    @GetMapping("/{chamiId}")
    public List<Visite> allVisitesByChami(@PathVariable("chamiId") String chamiId,
            @RequestHeader("Authorization") String jwt) {
        try {
            
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
            if(!token.getUid().equals(chamiId)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
            }
            return visiteService.getAllVisitesByChamiId(chamiId);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    // @GetMapping("/DTO/{chamiId}")
    // public List<VisiteDTO> allVisitesDTOByChami(@PathVariable("chamiId") String chamiId,
    //         @RequestHeader("Authorization") String jwt) {
    //     try {
            
    //         FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(jwt);
    //         if(!token.getUid().equals(chamiId)) {
    //             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    //         }
    //         return visiteService.getAllVisitesDTOByChamiId(chamiId);
    //     } catch (FirebaseAuthException e) {
    //         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
    //     }
    // }

    
    /** 
     * Met a joure une visite donnée
     * @param "idVisite"
     * @return 
     */
    @PutMapping("/{idVisite}")
    public Visite update(@PathVariable(value = "idVisite") int id, @RequestBody Visite visite,
            @RequestHeader("Authorization") String jwt) throws IOException {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            if (id == visite.getId()) {
                Optional<Visite> visiteTMP = visiteService.getVisite(id);
                if (!visiteTMP.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite à modifier n'existe pas");
                }
                Visite visiteF = visiteTMP.get();
                visiteF.setJoueur(visite.getJoueurs());
                visiteF.setRang(visite.getRang());
                return visiteService.saveVisite(visite);
            } else {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        "L'id passé en paramètre n'est pas le même que celui de la visite");
            }
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    
    /** 
     * Supprime une visite
     * @param id
     * @param jwt
     * @return 
     */
    @DeleteMapping("/{idVisite}")
    public void delete(@PathVariable(value = "idVisite") int id, @RequestHeader("Authorization") String jwt) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(jwt);
            Optional<Visite> visiteOpt = visiteService.getVisite(id);
            if (!visiteOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La visite n'existe pas");
            }
            visiteService.deleteVisite(id);
        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }


}
