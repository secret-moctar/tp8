package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PretRepository extends JpaRepository<Pret, Long> {
    List<Pret> findByUtilisateurEmail(String email);
}
