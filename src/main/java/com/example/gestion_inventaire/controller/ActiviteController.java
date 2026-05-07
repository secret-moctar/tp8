package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.service.ActiviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ActiviteController {

    private final ActiviteService activiteService;

    @GetMapping("/activites")
    public String afficherActivites(Model model) {
        model.addAttribute("activites", activiteService.listerActivites());
        return "activites"; // correspond à activites.html
    }
}
