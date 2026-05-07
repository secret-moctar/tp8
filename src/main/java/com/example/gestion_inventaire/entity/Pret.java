package com.example.gestion_inventaire.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPret;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters & Setters
    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
