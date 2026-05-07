package com.example.gestion_inventaire.repository;

import com.example.gestion_inventaire.entity.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepository extends JpaRepository<Mouvement,Long> {
}
