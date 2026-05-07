package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Utilisateur;
import com.example.gestion_inventaire.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    // 📌 Liste des utilisateurs
    @GetMapping
    public String pageUtilisateurs(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.listerUtilisateurs());
        model.addAttribute("activePage", "utilisateurs");
        return "utilisateurs/index"; // src/main/resources/templates/utilisateurs/index.html
    }

    // 📌 Formulaire d’ajout (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddUtilisateurForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateurs/add";
    }

    // 📌 Enregistrer un nouvel utilisateur
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String saveUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.ajouterUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    // 📌 Formulaire modification (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditUtilisateurForm(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs/edit";
    }

    // 📌 Sauvegarde modification (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateUtilisateur(@PathVariable Long id, @ModelAttribute Utilisateur utilisateurForm) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        utilisateur.setNom(utilisateurForm.getNom());
        utilisateur.setEmail(utilisateurForm.getEmail());
        utilisateur.setRole(utilisateurForm.getRole());

        utilisateurService.modifierUtilisateur(id, utilisateur);
        return "redirect:/utilisateurs";
    }

    // 📌 Supprimer un utilisateur (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return "redirect:/utilisateurs";
    }

    // 📌 Voir détail utilisateur
    @GetMapping("/voir/{id}")
    public String voirUtilisateur(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs/voir";
    }
}
