package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Activite;
import com.example.gestion_inventaire.repository.ActiviteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public Activite enregistrerActivite(String action, Long utilisateurId) {
        Activite activite = new Activite();
        activite.setAction(action);
        activite.setDateAction(LocalDateTime.now());

        // 🔹 ici on peut créer un lien avec l’utilisateur (si besoin de charger depuis repo)
        // activite.setUtilisateur(utilisateurRepository.findById(utilisateurId).orElse(null));

        return activiteRepository.save(activite);
    }

    public List<Activite> listerActivites() {
        return activiteRepository.findAll();
    }
}
