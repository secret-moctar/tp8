package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport,Long> {
}
