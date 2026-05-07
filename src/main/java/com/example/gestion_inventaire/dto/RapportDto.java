package com.example.gestion_inventaire.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RapportDto {
    private Long idRapport;
    private String type;
    private Date dateGeneration;
    private String contenu;

    private String utilisateurNom; // rapport généré par quel utilisateur
}
