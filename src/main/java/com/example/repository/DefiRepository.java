package com.example.repository;

import java.util.List;

import com.example.model.Chami;
import com.example.model.Defi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi, Integer> {

    List<Defi> findByAuteur(Chami chami);
}
