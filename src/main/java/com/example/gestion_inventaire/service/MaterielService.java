package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Materiel;
import com.example.gestion_inventaire.repository.MaterielRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterielService {

    private final MaterielRepository materielRepository;

    public MaterielService(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    // ✅ Ajouter un matériel
    public Materiel ajouterMateriel(Materiel materiel) {
        // Par défaut, on met la quantité à 0 si non renseignée
        if (materiel.getQuantite() < 0) {
            materiel.setQuantite(0);
        }

        // Par défaut, l’état est “Disponible”
        if (materiel.getEtat() == null || materiel.getEtat().isEmpty()) {
            materiel.setEtat("Disponible");
        }
        return materielRepository.save(materiel);
    }

    // ✅ Modifier un matériel existant
    public Materiel modifierMateriel(Long id, Materiel nouveauMateriel) {
        return materielRepository.findById(id)
                .map(m -> {
                    m.setNom(nouveauMateriel.getNom());
                    m.setDescription(nouveauMateriel.getDescription());
                    m.setQuantite(nouveauMateriel.getQuantite());
                    m.setEtat(nouveauMateriel.getEtat());
                    m.setCategorie(nouveauMateriel.getCategorie());
                    m.setPhotoUrl(nouveauMateriel.getPhotoUrl());
                    return materielRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Matériel non trouvé"));
    }

    // ✅ Supprimer un matériel
    public void supprimerMateriel(Long id) {
        if (!materielRepository.existsById(id)) {
            throw new RuntimeException("Matériel introuvable");
        }
        materielRepository.deleteById(id);
    }

    // ✅ Lister tous les matériels
    public List<Materiel> listerMateriels() {
        return materielRepository.findAll();
    }

    // ✅ Lister uniquement les matériels disponibles (quantité > 0)
    public List<Materiel> listerMaterielsDisponibles() {
        return materielRepository.findByQuantiteGreaterThan(0);
    }

    // ✅ Trouver un matériel par ID
    public Optional<Materiel> trouverParId(Long id) {
        return materielRepository.findById(id);
    }
}
