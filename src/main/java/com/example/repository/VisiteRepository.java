package com.example.repository;

import java.util.List;

import com.example.model.Visite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteRepository extends JpaRepository<Visite, Integer> {

    
    List<Visite> findByJoueursLogin(String joueurId);
}
