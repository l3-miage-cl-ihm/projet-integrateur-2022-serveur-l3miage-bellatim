package com.example.repository;


import com.example.model.Defi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi, String> {

}
    

