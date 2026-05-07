package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Mouvement;
import com.example.gestion_inventaire.service.MouvementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mouvements")
public class MouvementController {

    private final MouvementService mouvementService;

    // 📌 Page liste mouvements
    @GetMapping
    public String pageMouvements(Model model) {
        model.addAttribute("mouvements", mouvementService.listerMouvements());
        model.addAttribute("activePage", "mouvements");
        return "mouvements/index"; // templates/mouvements/index.html
    }

    // 📌 Formulaire ajout
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("mouvement", new Mouvement());
        return "mouvements/add"; // templates/mouvements/add.html
    }

    // 📌 Enregistrer un mouvement (ajout)
    @PostMapping("/add")
    public String saveMouvement(@ModelAttribute Mouvement mouvement) {
        mouvementService.enregistrerMouvement(mouvement);
        return "redirect:/mouvements";
    }

    // 📌 Formulaire modification
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Mouvement mouvement = mouvementService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Mouvement introuvable"));
        model.addAttribute("mouvement", mouvement);
        return "mouvements/edit"; // templates/mouvements/edit.html
    }

    // 📌 Sauvegarder modification
    @PostMapping("/edit/{id}")
    public String updateMouvement(@PathVariable Long id, @ModelAttribute Mouvement mouvementForm) {
        mouvementService.modifierMouvement(id, mouvementForm);
        return "redirect:/mouvements";
    }

    // 📌 Supprimer
    @PostMapping("/delete/{id}")
    public String deleteMouvement(@PathVariable Long id) {
        mouvementService.supprimerMouvement(id);
        return "redirect:/mouvements";
    }

    // 📌 Voir détail
    @GetMapping("/voir/{id}")
    public String voirMouvement(@PathVariable Long id, Model model) {
        Mouvement mouvement = mouvementService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Mouvement introuvable"));
        model.addAttribute("mouvement", mouvement);
        return "mouvements/voir"; // templates/mouvements/voir.html
    }
}
