package com.example.controller;


import java.util.List;
import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;
import com.example.model.Etape;
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

    @GetMapping("/insert")
    public String testInsert(){
        String ret = "";
        Chami chami = new Chami("testChami",37,"email");
        Defi defi = new Defi("idDefi","titreDefi",null,chami,);
        return ret;
    }
    
}
