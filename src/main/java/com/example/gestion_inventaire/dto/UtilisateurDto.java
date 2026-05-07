package com.example.gestion_inventaire.dto;

import lombok.Data;

@Data
public class UtilisateurDto {
    private Long idUtilisateur;
    private String nom;
    private String email;

    private String roleNom; // au lieu d'envoyer l'objet Role entier
}
