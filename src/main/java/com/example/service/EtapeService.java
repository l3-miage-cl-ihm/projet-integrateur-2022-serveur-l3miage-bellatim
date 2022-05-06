package com.example.service;

import com.example.model.Defi;
import com.example.model.Etape;
import com.example.repository.DefiRepository;
import com.example.repository.EtapeRespository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtapeService {

    @Autowired
    private EtapeRespository itemRepository;

    public Optional<Etape> getItem(final int id) {
        return itemRepository.findById(id);
    }

    public List<Etape> getAllItem() {
        return itemRepository.findAll();
    }

    public void deleteItem(final int id) {
        itemRepository.deleteById(id);
    }

    public Etape saveItem(Etape item) {
        return itemRepository.save(item);
    }

    /*@Autowired
    private DefiRepository defiRepository;

    /*public List<Etape> getAllItemByDefi(int defiId) {
        Optional<Defi> defiOpt = defiRepository.findById(defiId);
        if (defiOpt.isPresent()) {
            return itemRepository.findByDefi(defiOpt.get());
        } else {
            // return new ArrayList<Etape>();
            return null;
        }
    }*/
}
