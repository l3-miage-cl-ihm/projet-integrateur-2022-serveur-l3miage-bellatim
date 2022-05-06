package com.example.repository;

import java.util.List;

import com.example.model.Defi;
import com.example.model.Etape;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapeRespository extends JpaRepository<Etape, Integer> {
    //List<Etape> findByDefi(Defi defi);
}
