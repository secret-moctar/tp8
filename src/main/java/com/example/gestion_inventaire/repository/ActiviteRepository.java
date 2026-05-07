package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
