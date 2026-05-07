package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Materiel;
import com.example.gestion_inventaire.entity.Pret;
import com.example.gestion_inventaire.service.MaterielService;
import com.example.gestion_inventaire.service.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prets")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private MaterielService materielService;

    // ----------------- Lister les prêts -----------------
    @GetMapping
    public String listerPrets(Authentication authentication, Model model) {
        List<Pret> prets;
        boolean isUtilisateur = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_UTILISATEUR"));

        if (isUtilisateur) {
            prets = pretService.listerPretsParUtilisateur(authentication.getName());
        } else {
            prets = pretService.listerPrets();
        }

        model.addAttribute("prets", prets);
        model.addAttribute("activePage", "prets");
        return "prets/index";
    }

    // ----------------- Formulaire pour demander un prêt -----------------
    @GetMapping("/add")
    public String formAjouterPret(Model model) {
        model.addAttribute("materiels", materielService.listerMaterielsDisponibles());
        return "prets/add";
    }

    // ----------------- Créer une demande de prêt -----------------
    @PostMapping("/add")
    public String ajouterPret(@RequestParam Long materielId, Authentication authentication) {
        pretService.creerPret(materielId, authentication.getName());
        return "redirect:/prets";
    }

    // ----------------- Valider ou refuser un prêt (gestionnaire) -----------------
    @PostMapping("/{id}/valider")
    public String validerPret(@PathVariable Long id, @RequestParam boolean accepter) {
        pretService.validerPret(id, accepter);
        return "redirect:/prets";
    }

    // ----------------- Enregistrer le retour -----------------
    @PostMapping("/{id}/retour")
    public String enregistrerRetour(@PathVariable Long id) {
        pretService.enregistrerRetour(id);
        return "redirect:/prets";
    }

    // ----------------- Supprimer un prêt -----------------
    @PostMapping("/delete/{id}")
    public String supprimerPret(@PathVariable Long id) {
        pretService.supprimerPret(id);
        return "redirect:/prets";
    }

    // ----------------- Voir un prêt -----------------
    @GetMapping("/voir/{id}")
    public String voirPret(@PathVariable Long id, Model model) {
        Pret pret = pretService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Prêt introuvable"));
        model.addAttribute("pret", pret);
        return "prets/voir";
    }
}
