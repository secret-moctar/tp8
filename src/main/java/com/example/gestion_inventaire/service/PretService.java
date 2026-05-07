package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Materiel;
import com.example.gestion_inventaire.entity.Pret;
import com.example.gestion_inventaire.entity.Utilisateur;
import com.example.gestion_inventaire.repository.MaterielRepository;
import com.example.gestion_inventaire.repository.PretRepository;
import com.example.gestion_inventaire.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    private final PretRepository pretRepository;
    private final MaterielRepository materielRepository;
    private final UtilisateurRepository utilisateurRepository;

    public PretService(PretRepository pretRepository,
                       MaterielRepository materielRepository,
                       UtilisateurRepository utilisateurRepository) {
        this.pretRepository = pretRepository;
        this.materielRepository = materielRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // ----------------- Créer une demande de prêt -----------------
    public Pret creerPret(Long idMateriel, String emailUtilisateur) {
        Materiel materiel = materielRepository.findById(idMateriel)
                .orElseThrow(() -> new RuntimeException("Matériel introuvable"));
        Utilisateur utilisateur = utilisateurRepository.findByEmail(emailUtilisateur)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (materiel.getQuantite() <= 0) {
            throw new RuntimeException("Matériel non disponible pour le prêt");
        }

        Pret pret = new Pret();
        pret.setMateriel(materiel);
        pret.setUtilisateur(utilisateur);
        pret.setDateDebut(new Date());
        pret.setStatut("EN_ATTENTE"); // statut initial

        return pretRepository.save(pret);
    }

    // ----------------- Valider ou refuser un prêt (gestionnaire) -----------------
    public Pret validerPret(Long id, boolean accepter) {
        Pret pret = pretRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));

        if (!"EN_ATTENTE".equalsIgnoreCase(pret.getStatut())) {
            throw new RuntimeException("Ce prêt ne peut pas être validé/refusé");
        }

        if (accepter) {
            pret.setStatut("EN_COURS");
            // Décrémenter la quantité disponible
            Materiel materiel = pret.getMateriel();
            if (materiel.getQuantite() <= 0) {
                throw new RuntimeException("Matériel non disponible pour validation");
            }
            materiel.setQuantite(materiel.getQuantite() - 1);
            materielRepository.save(materiel);
        } else {
            pret.setStatut("REFUSE");
        }

        return pretRepository.save(pret);
    }

    // ----------------- Enregistrer un retour -----------------
    public Pret enregistrerRetour(Long id) {
        Pret pret = pretRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));

        if (!"EN_COURS".equalsIgnoreCase(pret.getStatut())) {
            throw new RuntimeException("Le prêt n'est pas en cours");
        }

        pret.setDateFin(new Date());
        pret.setStatut("TERMINE");

        // Incrémenter la quantité du matériel
        Materiel materiel = pret.getMateriel();
        materiel.setQuantite(materiel.getQuantite() + 1);
        materielRepository.save(materiel);

        return pretRepository.save(pret);
    }

    // ----------------- Lister tous les prêts -----------------
    public List<Pret> listerPrets() {
        return pretRepository.findAll();
    }

    // ----------------- Lister les prêts d’un utilisateur -----------------
    public List<Pret> listerPretsParUtilisateur(String email) {
        return pretRepository.findByUtilisateurEmail(email);
    }

    // ----------------- Trouver par ID -----------------
    public Optional<Pret> trouverParId(Long id) {
        return pretRepository.findById(id);
    }

    // ----------------- Supprimer un prêt -----------------
    public void supprimerPret(Long id) {
        pretRepository.deleteById(id);
    }
}
