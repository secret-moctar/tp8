package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Categorie;
import com.example.gestion_inventaire.entity.Materiel;
import com.example.gestion_inventaire.entity.Pret;
import com.example.gestion_inventaire.entity.Mouvement;
import com.example.gestion_inventaire.service.CategorieService;
import com.example.gestion_inventaire.service.MaterielService;
import com.example.gestion_inventaire.service.UtilisateurService;
import com.example.gestion_inventaire.service.PretService;
import com.example.gestion_inventaire.service.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private MaterielService materielService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private PretService pretService;

    @Autowired
    private MouvementService mouvementService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Authentication authentication, Model model) {

        // ---------------------- Stats communes ----------------------
        List<Materiel> tousMateriels = materielService.listerMateriels();
        model.addAttribute("totalMateriels", tousMateriels.size());
        model.addAttribute("totalUtilisateurs", utilisateurService.listerUtilisateurs().size());

        // Derniers matériels ajoutés (5 max)
        List<Materiel> derniersMateriels = tousMateriels.stream()
                .sorted(Comparator.comparing(Materiel::getDateAcquisition).reversed())
                .limit(5)
                .collect(Collectors.toList());
        model.addAttribute("derniersMateriels", derniersMateriels);

        // Quantité par catégorie
        Map<String, Integer> quantiteParCategorie = new LinkedHashMap<>();
        for (Categorie c : categorieService.listerCategories()) {
            int total = tousMateriels.stream()
                    .filter(m -> m.getCategorie().getIdCategorie().equals(c.getIdCategorie()))
                    .mapToInt(Materiel::getQuantite)
                    .sum();
            quantiteParCategorie.put(c.getNom(), total);
        }
        model.addAttribute("quantiteParCategorie", quantiteParCategorie);

        // ---------------------- Vérification des rôles ----------------------
        boolean isAdminOrGestionnaire = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_GESTIONNAIRE"));

        boolean isUtilisateur = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_UTILISATEUR"));

        // ---------------------- Contenu ADMIN / GESTIONNAIRE ----------------------
        if (isAdminOrGestionnaire) {
            // Derniers mouvements
            List<Mouvement> derniersMouvements = mouvementService.listerMouvements().stream()
                    .sorted(Comparator.comparing(Mouvement::getDateMouvement).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
            model.addAttribute("derniersMouvements", derniersMouvements);

            // Derniers prêts
            List<Pret> derniersPrets = pretService.listerPrets().stream()
                    .sorted(Comparator.comparing(Pret::getDateDebut).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
            model.addAttribute("derniersPrets", derniersPrets);

            // Stats globales
            model.addAttribute("totalMouvements", mouvementService.listerMouvements().size());
            model.addAttribute("totalPrets", pretService.listerPrets().size());
        }

        // ---------------------- Contenu UTILISATEUR ----------------------
        if (isUtilisateur) {
            List<Pret> mesPrets = pretService.listerPretsParUtilisateur(authentication.getName());
            model.addAttribute("mesPrets", mesPrets);
            model.addAttribute("totalPrets", mesPrets.size());
        }

        // ---------------------- Sidebar actif ----------------------
        model.addAttribute("activePage", "dashboard");

        return "index"; // template unique pour tous les rôles
    }
}
