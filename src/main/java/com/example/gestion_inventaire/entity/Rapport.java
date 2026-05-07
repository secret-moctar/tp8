package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRapport;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateGeneration;

    @Lob
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters & Setters
    public Long getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(Long idRapport) {
        this.idRapport = idRapport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateGeneration() {
        return dateGeneration;
    }

    public void setDateGeneration(Date dateGeneration) {
        this.dateGeneration = dateGeneration;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
