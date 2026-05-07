package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Mouvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMouvement;

    private String type;
    private int quantite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMouvement;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters & Setters
    public Long getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Long idMouvement) {
        this.idMouvement = idMouvement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
