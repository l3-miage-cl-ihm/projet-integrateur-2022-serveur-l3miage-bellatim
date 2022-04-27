package com.example.repository;

import com.example.model.Chami;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamiRepository extends JpaRepository<Chami, String> {
    

}
