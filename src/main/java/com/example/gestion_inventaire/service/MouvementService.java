package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Mouvement;
import com.example.gestion_inventaire.repository.MouvementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MouvementService {

    private final MouvementRepository mouvementRepository;

    public MouvementService(MouvementRepository mouvementRepository) {
        this.mouvementRepository = mouvementRepository;
    }

    // Enregistrer ou ajouter un mouvement
    public Mouvement enregistrerMouvement(Mouvement mouvement) {
        return mouvementRepository.save(mouvement);
    }

    // Modifier un mouvement existant
    public Mouvement modifierMouvement(Long id, Mouvement mouvementMaj) {
        return mouvementRepository.findById(id).map(m -> {
            m.setType(mouvementMaj.getType());
            m.setDateMouvement(mouvementMaj.getDateMouvement());
            m.setQuantite(mouvementMaj.getQuantite());
            m.setMateriel(mouvementMaj.getMateriel());
            m.setUtilisateur(mouvementMaj.getUtilisateur());
            return mouvementRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Mouvement non trouvé"));
    }

    // Supprimer
    public void supprimerMouvement(Long id) {
        mouvementRepository.deleteById(id);
    }

    // Lister
    public List<Mouvement> listerMouvements() {
        return mouvementRepository.findAll();
    }

    // Trouver par id
    public Optional<Mouvement> trouverParId(Long id) {
        return mouvementRepository.findById(id);
    }
}
