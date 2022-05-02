package com.example.controller;


import java.util.List;
import java.util.Optional;

import com.example.model.Categorie;
import com.example.model.Chami;
import com.example.model.Defi;
import com.example.model.Etape;
import com.example.model.Media;
import com.example.service.ChamiService;
import com.example.service.DefiService;
import com.example.service.EtapeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@CrossOrigin
@RequestMapping(value="/api/test")
public class TestController {
    @Autowired 
    private EtapeService etapeService;

    @Autowired
    private DefiService defiService;

    @GetMapping("/")
    public List<Etape> allItems(){

        return etapeService.getAllItem();
    }

    @Autowired
    ChamiService chamiService;

    @ResponseBody
    @GetMapping("/insert")
    public String testInsert(){
        String ret = "";
        Chami chami = new Chami("testChami",37,"email");
        Defi defi = new Defi("idDefi","titreDefi",null,chami,Categorie.SPORTIF);
        Etape etape = new Etape(12,"labelEtape",defi);
        Etape etape2 = new Etape(13,"labelEtape13",defi);
        Media media = new Media(14,"labelMedia",defi,null,null);
        defi.addEtape(etape);
        defi.addEtape(etape2);
        // etape.setDefi(defi);

        chamiService.saveChami(chami);
        defiService.saveDefi(defi);
        etapeService.saveItem(etape);
        etapeService.saveItem(etape2);
        etapeService.saveItem(media);

        ret+="chami: "+chami+"<br>";
        ret+="defi: "+defi+"<br>";
        ret+="etape: "+etape+"<br>";
        ret+="media: "+media+"<br>";
        return ret;
    }
    
}
