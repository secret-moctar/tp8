package com.example.gestion_inventaire.controller;

import com.example.gestion_inventaire.entity.Role;
import com.example.gestion_inventaire.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> listerRoles() {
        return roleService.listerRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> trouverRoleParId(@PathVariable Long id) {
        return roleService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Role ajouterRole(@RequestBody Role role) {
        return roleService.ajouterRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> modifierRole(@PathVariable Long id, @RequestBody Role nouveauRole) {
        try {
            return ResponseEntity.ok(roleService.modifierRole(id, nouveauRole));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRole(@PathVariable Long id) {
        roleService.supprimerRole(id);
        return ResponseEntity.noContent().build();
    }
}
