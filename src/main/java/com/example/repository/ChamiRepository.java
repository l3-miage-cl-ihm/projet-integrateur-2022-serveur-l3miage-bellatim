package com.example.repository;

import com.example.model.Chami;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamiRepository extends JpaRepository<Chami, String> {
    
    Optional<Chami> findByEmail(String email);

    List<Chami> findByVisitesId(int visiteId);
}
