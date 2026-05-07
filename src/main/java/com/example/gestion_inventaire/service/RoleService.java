package com.example.gestion_inventaire.service;

import com.example.gestion_inventaire.entity.Role;
import com.example.gestion_inventaire.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role ajouterRole(Role role) {
        return roleRepository.save(role);
    }

    public Role modifierRole(Long id, Role nouveauRole) {
        return roleRepository.findById(id).map(r -> {
            r.setNom(nouveauRole.getNom());
            r.setPermissions(nouveauRole.getPermissions());
            return roleRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
    }

    public void supprimerRole(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> listerRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> trouverParId(Long id) {
        return roleRepository.findById(id);
    }
}
