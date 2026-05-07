package com.example.gestion_inventaire.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;

    private String nom;
    private String description;
    private String etat;
    private int quantite;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAcquisition;

    private String photoUrl; // <-- URL de la photo

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mouvement> mouvements;

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pret> prets;

    @Transient
    private Long idCategorie; // pour le binding du formulaire


    // Constructeurs
    public Materiel() {}

    public Materiel(String nom, String description, String etat, int quantite, Date dateAcquisition, String photoUrl, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.quantite = quantite;
        this.dateAcquisition = dateAcquisition;
        this.photoUrl = photoUrl;
        this.categorie = categorie;
    }

    // Getters & Setters
    public Long getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(Long idMateriel) {
        this.idMateriel = idMateriel;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(Date dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    public List<Pret> getPrets() {
        return prets;
    }

    public void setPrets(List<Pret> prets) {
        this.prets = prets;
    }

    // Getters & Setters
    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }


}
