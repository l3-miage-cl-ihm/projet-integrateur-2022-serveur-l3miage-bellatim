package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.Etape;
import com.example.service.EtapeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin
@RequestMapping(value="/api/etape")
public class EtapeControleur {

    @Autowired
    private EtapeService etapeService;

    @GetMapping("/")
    public List<Etape> allItems(){
        return etapeService.getAllItem();
    }

    @GetMapping("/{itemId}")
    public Etape read(@PathVariable(value = "itemId") int id){
        Optional<Etape> item = etapeService.getItem(id);

        if(!item.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune étape n'existe à cet id");
        }

        return item.get();
    }

    @PostMapping("/create")
    public Etape create(@RequestBody Etape etape){
        return etapeService.saveItem(etape);
    }

    @PutMapping(value="/{idEtape}")
    public Etape update(@PathVariable(value = "idetape") int id, @RequestBody Etape etape) {
        
        if(id != etape.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "l'id de l'étape passé en paramètre est différent de celui saisi.");
        }

        Optional<Etape> etapeTMP = etapeService.getItem(id);

        if(!etapeTMP.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun item de description n'existe à cet id");
        }

        Etape newEtape = etapeTMP.get();
        newEtape.setRang(etape.getRang());
        newEtape.setLabel(etape.getLabel());

        return etapeService.saveItem(newEtape);
    }

    @DeleteMapping("/{idEtape}")
    public void delete(@PathVariable(value = "idEtape") int id){
        etapeService.deleteItem(id);
    }
    
}
