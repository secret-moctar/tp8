package com.example.gestion_inventaire.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaterielDto {
    private Long idMateriel;
    private String nom;
    private String description;
    private String etat;
    private int quantite;
    private Date dateAcquisition;
    private String categorieNom; // au lieu d'envoyer tout l'objet Categorie
    private String photoUrl;
}
