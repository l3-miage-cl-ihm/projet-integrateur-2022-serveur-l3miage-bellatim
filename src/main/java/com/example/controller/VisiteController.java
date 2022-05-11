package com.example.controller;

import com.example.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    //////////////// TEST///////////////

    @GetMapping("/insert")
    public List<Visite> testVisite() {
        return null;
        // Chami c1 = new Chami("toto", 12, "toto@gmail.com");
        // Chami c2 = new Chami("joe", 12, "joe@gmail.com");
        // Chami c3 = new Chami("francois", 10, "francois@gmail.com");
        // Chami c4 = new Chami("david", 9, "david@gmail.com");

        // List<Chami> joueurs = new ArrayList<>();
        // joueurs.add(c1);
        // joueurs.add(c2);
        // joueurs.add(c3);
        // joueurs.add(c4);

        // Defi d = new Defi("D199", "fais du sport", null, c1, Categorie.SPORTIF,
        // null);

        // Etape e1 = new Etape(1, "il faut chercher l'indice 1", d);
        // Etape e2 = new Etape(2, "il faut chercher l'indice 2", d);
        // Etape e3 = new Etape(3, "il faut chercher l'indice 3", d);
        // Etape e4 = new Etape(4, "il faut chercher l'indice 4", d);
        // Etape e5 = new Etape(5, "il faut chercher l'indice 5", d);
        // Media m1 = new Media(1, "regarde la photo 1", d, "maphoto.com");

        // ArrayList<Etape> etapes = new ArrayList<>();
        // etapes.add(e1);
        // etapes.add(e2);
        // etapes.add(e3);
        // etapes.add(e4);
        // etapes.add(e5);
        // etapes.add(m1);

        // d.setEtape(etapes);

        // Visite v1 = new Visite(joueurs, d, 0);
        // visiteService.saveVisite(v1);

        // List<Chami> joueurs2 = new ArrayList<>();
        // joueurs2.add(c2);
        // Defi d2 = new Defi("D021", "Lis un livre", null, c1, Categorie.CULTUREL,
        // null);
        // Etape e6 = new Etape(1, "il faut chercher l'indice 1", d2);
        // ArrayList<Etape> etapes2 = new ArrayList<>();
        // etapes2.add(e6);
        // d2.setEtape(etapes2);
        // Visite v2 = new Visite(joueurs2, d2, 0);
        // List<Visite> lesVisites = new ArrayList<>();
        // lesVisites.add(v1);
        // lesVisites.add(v2);
        // visiteService.saveVisite(v2);

        // return lesVisites;

    }
}
