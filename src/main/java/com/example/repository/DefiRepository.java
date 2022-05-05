package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Chami;
import com.example.model.Defi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi, Integer> {

    Optional<Defi> findByTitre(String titre);

    List<Defi> findByAuteur(Chami chami);
}
