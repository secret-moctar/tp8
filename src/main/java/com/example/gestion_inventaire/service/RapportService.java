package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Rapport;
import com.example.gestion_inventaire.repository.RapportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapportService {

    private final RapportRepository rapportRepository;

    public RapportService(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public Rapport genererRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public void supprimerRapport(Long id) {
        rapportRepository.deleteById(id);
    }

    public List<Rapport> listerRapports() {
        return rapportRepository.findAll();
    }

    public Optional<Rapport> trouverParId(Long id) {
        return rapportRepository.findById(id);
    }
}
