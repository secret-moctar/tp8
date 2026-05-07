package com.example.gestion_inventaire.security;

import com.example.gestion_inventaire.entity.Utilisateur;
import com.example.gestion_inventaire.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email));
        return new UtilisateurDetails(utilisateur);
    }
}
