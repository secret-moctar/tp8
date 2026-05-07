package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}