package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivite;

    private String action;

    private LocalDateTime dateAction;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters & Setters
    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        this.dateAction = dateAction;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
