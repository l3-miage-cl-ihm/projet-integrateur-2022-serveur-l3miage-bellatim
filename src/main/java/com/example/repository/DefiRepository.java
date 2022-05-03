package com.example.repository;


import java.util.List;

import com.example.model.Chami;
import com.example.model.Defi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi, String> {
    
<<<<<<< HEAD
    List<Defi> findByAuteur(String id);
=======
    List<Defi> findByAuteur(Chami chami);
>>>>>>> 2b71b15cd3b3b18900df8be58a8fc947853a81dd
}
    

