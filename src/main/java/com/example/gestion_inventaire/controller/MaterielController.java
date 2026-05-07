package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Categorie;
import com.example.gestion_inventaire.entity.Materiel;
import com.example.gestion_inventaire.service.CategorieService;
import com.example.gestion_inventaire.service.MaterielService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/materiels")
public class MaterielController {

    private final MaterielService materielService;
    private final CategorieService categorieService;

    // 📌 Liste des matériels (accessible à tous)
    @GetMapping
    public String pageMateriels(Model model) {
        List<Materiel> materiels = materielService.listerMateriels();
        model.addAttribute("materiels", materiels);
        model.addAttribute("activePage", "materiels");
        return "materiels/index"; // → rend index.html
    }

    // 📌 Formulaire d’ajout (ADMIN et GESTIONNAIRE uniquement)
    @PreAuthorize("hasAnyRole('ADMIN','GESTIONNAIRE')")
    @GetMapping("/add")
    public String showAddMaterielForm(Model model) {
        model.addAttribute("materiel", new Materiel()); // objet vide pour le form
        model.addAttribute("categories", categorieService.listerCategories());
        return "materiels/add"; // → rend add.html
    }

    // 📌 Enregistrer un matériel (ajout)
    @PreAuthorize("hasAnyRole('ADMIN','GESTIONNAIRE')")
    @PostMapping("/add")
    public String saveMateriel(@ModelAttribute Materiel materiel) {
        if (materiel.getIdCategorie() != null) {
            Categorie cat = categorieService.trouverParId(materiel.getIdCategorie())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            materiel.setCategorie(cat);
        }
        materielService.ajouterMateriel(materiel);
        return "redirect:/materiels";
    }




    // 📌 Supprimer (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteMateriel(@PathVariable Long id) {
        materielService.supprimerMateriel(id);
        return "redirect:/materiels";
    }

    // 📌 Formulaire modification (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditMaterielForm(@PathVariable Long id, Model model) {
        Materiel materiel = materielService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Matériel introuvable"));
        model.addAttribute("materiel", materiel);
        model.addAttribute("categories", categorieService.listerCategories());
        return "materiels/edit"; // → rend edit.html
    }

    // 📌 Sauvegarde modification (ADMIN seulement)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateMateriel(@PathVariable Long id, @ModelAttribute Materiel materielForm) {
        Materiel materiel = materielService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Matériel introuvable"));

        // Mettre à jour uniquement les champs modifiables
        materiel.setNom(materielForm.getNom());
        materiel.setDescription(materielForm.getDescription());
        materiel.setQuantite(materielForm.getQuantite());
        materiel.setDateAcquisition(materielForm.getDateAcquisition());

        if (materielForm.getIdCategorie() != null) {
            Categorie cat = categorieService.trouverParId(materielForm.getIdCategorie())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            materiel.setCategorie(cat);
        }

        materielService.ajouterMateriel(materiel); // save/update
        return "redirect:/materiels";
    }

    // 📌 Voir détail (accessible à tous)
    @GetMapping("/voir/{id}")
    public String voirMateriel(@PathVariable Long id, Model model) {
        Materiel materiel = materielService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Matériel introuvable"));
        model.addAttribute("materiel", materiel);
        return "materiels/voir"; // → rend voir.html
    }
}
