package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Categorie;
import com.example.gestion_inventaire.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie ajouterCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie modifierCategorie(Long id, Categorie nouvelleCategorie) {
        return categorieRepository.findById(id).map(c -> {
            c.setNom(nouvelleCategorie.getNom());
            c.setDescription(nouvelleCategorie.getDescription());
            return categorieRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Categorie non trouvée"));
    }

    public void supprimerCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    public List<Categorie> listerCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> trouverParId(Long id) {
        return categorieRepository.findById(id);
    }
}
