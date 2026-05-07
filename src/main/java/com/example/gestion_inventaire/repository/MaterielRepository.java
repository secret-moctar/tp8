package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    List<Materiel> findByQuantiteGreaterThan(int quantite);
}
