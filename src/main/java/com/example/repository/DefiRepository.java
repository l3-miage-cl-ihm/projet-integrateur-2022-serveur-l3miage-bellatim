package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.Defi;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DefiRepository extends JpaRepository<Defi, String> {
    
}
