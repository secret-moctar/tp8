package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Materiel> materiels;

    // Getters & Setters
    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }
}
