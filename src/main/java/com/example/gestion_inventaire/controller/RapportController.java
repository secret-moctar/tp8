package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Rapport;
import com.example.gestion_inventaire.service.RapportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@RequiredArgsConstructor
public class RapportController {

    private final RapportService rapportService;

    @GetMapping
    public List<Rapport> listerRapports() {
        return rapportService.listerRapports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rapport> trouverRapportParId(@PathVariable Long id) {
        return rapportService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rapport genererRapport(@RequestBody Rapport rapport) {
        return rapportService.genererRapport(rapport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRapport(@PathVariable Long id) {
        rapportService.supprimerRapport(id);
        return ResponseEntity.noContent().build();
    }
}
