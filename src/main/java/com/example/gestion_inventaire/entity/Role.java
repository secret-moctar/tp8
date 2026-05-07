package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String nom;
    private String permissions;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

    // Getters & Setters
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
