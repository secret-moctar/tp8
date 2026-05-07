package com.example.gestion_inventaire.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    private String nom;
    private String email;
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    private List<Mouvement> mouvements;

    @OneToMany(mappedBy = "utilisateur")
    private List<Pret> prets;

    @OneToMany(mappedBy = "utilisateur")
    private List<Rapport> rapports;

    // Getters & Setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public List<Rapport> getRapports() {
        return rapports;
    }

    public void setRapports(List<Rapport> rapports) {
        this.rapports = rapports;
    }
}
