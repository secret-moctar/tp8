package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Utilisateur;
import com.example.gestion_inventaire.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ActiviteService activiteService;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, ActiviteService activiteService) {
        this.utilisateurRepository = utilisateurRepository;
        this.activiteService = activiteService;
    }

    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        activiteService.enregistrerActivite("Ajout d'un nouvel utilisateur : " + utilisateur.getNom(), saved.getIdUtilisateur());
        return saved;
    }

    public Utilisateur modifierUtilisateur(Long id, Utilisateur nouvelUtilisateur) {
        return utilisateurRepository.findById(id).map(u -> {
            u.setNom(nouvelUtilisateur.getNom());
            u.setEmail(nouvelUtilisateur.getEmail());
            u.setMotDePasse(nouvelUtilisateur.getMotDePasse());
            u.setRole(nouvelUtilisateur.getRole());
            Utilisateur updated = utilisateurRepository.save(u);
            activiteService.enregistrerActivite("Modification de l'utilisateur : " + u.getNom(), u.getIdUtilisateur());
            return updated;
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.findById(id).ifPresent(u -> {
            activiteService.enregistrerActivite("Suppression de l'utilisateur : " + u.getNom(), u.getIdUtilisateur());
        });
        utilisateurRepository.deleteById(id);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepository.findById(id);
    }
}
