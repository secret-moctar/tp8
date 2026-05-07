package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Categorie;
import com.example.gestion_inventaire.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    // Liste des catégories
    @GetMapping
    public String listerCategories(Model model) {
        model.addAttribute("categories", categorieService.listerCategories());
        return "categories/index"; // templates/categories/index.html
    }

    // Afficher le formulaire d'ajout
    @GetMapping("/add")
    public String afficherFormAjout(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categories/add"; // templates/categories/add.html
    }

    // Sauvegarder une nouvelle catégorie
    @PostMapping("/add")
    public String ajouterCategorie(@ModelAttribute Categorie categorie) {
        categorieService.ajouterCategorie(categorie);
        return "redirect:/categories";
    }

    // Afficher le formulaire de modification
    @GetMapping("/edit/{id}")
    public String afficherFormEdit(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        model.addAttribute("categorie", categorie);
        return "categories/edit"; // templates/categories/edit.html
    }

    // Sauvegarder la modification
    @PostMapping("/edit/{id}")
    public String modifierCategorie(@PathVariable Long id, @ModelAttribute Categorie nouvelleCategorie) {
        categorieService.modifierCategorie(id, nouvelleCategorie);
        return "redirect:/categories";
    }

    // Supprimer une catégorie
    @GetMapping("/delete/{id}")
    public String supprimerCategorie(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
        return "redirect:/categories";
    }

    // Détails d'une catégorie
    @GetMapping("/view/{id}")
    public String voirCategorie(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        model.addAttribute("categorie", categorie);
        return "categories/view"; // templates/categories/view.html
    }
}
