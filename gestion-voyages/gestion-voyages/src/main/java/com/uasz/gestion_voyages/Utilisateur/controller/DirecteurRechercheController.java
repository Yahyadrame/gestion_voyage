package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Utilisateur.dto.*;
import com.uasz.gestion_voyages.Utilisateur.modele.*;
import com.uasz.gestion_voyages.Utilisateur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drc")
public class DirecteurRechercheController {

    @Autowired
    private DirecteurRechercheService directeurRechercheService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private DirecteurRHService directeurRHService;

    @Autowired
    private DirecteurFinancierService directeurFinancierService;

    // === Gestion des DRC ===
    @GetMapping
    public ResponseEntity<List<DirecteurRechercheDTO>> listerDirecteursRecherche() {
        return ResponseEntity.ok(directeurRechercheService.listerDirecteursRecherche());
    }

    @PostMapping
    public ResponseEntity<DirecteurRechercheDTO> ajouterDirecteurRecherche(@RequestBody DirecteurRecherche directeurRecherche) {
        return ResponseEntity.ok(directeurRechercheService.ajouterDirecteurRecherche(directeurRecherche));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirecteurRechercheDTO> obtenirDirecteurRecherche(@PathVariable Long id) {
        return ResponseEntity.ok(directeurRechercheService.obtenirDirecteurRecherche(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirecteurRechercheDTO> modifierDirecteurRecherche(@PathVariable Long id, @RequestBody DirecteurRecherche directeurRecherche) {
        return ResponseEntity.ok(directeurRechercheService.modifierDirecteurRecherche(id, directeurRecherche));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDirecteurRecherche(@PathVariable Long id) {
        directeurRechercheService.supprimerDirecteurRecherche(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des enseignants ===
    @PostMapping("/enseignants")
    public ResponseEntity<EnseignantDTO> ajouterEnseignant(@RequestBody Enseignant enseignant) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(enseignantService.ajouterEnseignant(enseignant));
    }

    @PutMapping("/enseignants/{id}")
    public ResponseEntity<EnseignantDTO> modifierEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(enseignantService.modifierEnseignant(id, enseignant));
    }

    @DeleteMapping("/enseignants/{id}")
    public ResponseEntity<Void> supprimerEnseignant(@PathVariable Long id) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        enseignantService.supprimerEnseignant(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DRH ===
    @PostMapping("/drh")
    public ResponseEntity<DirecteurRHDTO> ajouterDirecteurRH(@RequestBody DirecteurRH directeurRH) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(directeurRHService.ajouterDirecteurRH(directeurRH));
    }

    @PutMapping("/drh/{id}")
    public ResponseEntity<DirecteurRHDTO> modifierDirecteurRH(@PathVariable Long id, @RequestBody DirecteurRH directeurRH) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(directeurRHService.modifierDirecteurRH(id, directeurRH));
    }

    @DeleteMapping("/drh/{id}")
    public ResponseEntity<Void> supprimerDirecteurRH(@PathVariable Long id) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        directeurRHService.supprimerDirecteurRH(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DFC ===
    @PostMapping("/dfc")
    public ResponseEntity<DirecteurFinancierDTO> ajouterDirecteurFinancier(@RequestBody DirecteurFinancier directeurFinancier) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(directeurFinancierService.ajouterDirecteurFinancier(directeurFinancier));
    }

    @PutMapping("/dfc/{id}")
    public ResponseEntity<DirecteurFinancierDTO> modifierDirecteurFinancier(@PathVariable Long id, @RequestBody DirecteurFinancier directeurFinancier) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        return ResponseEntity.ok(directeurFinancierService.modifierDirecteurFinancier(id, directeurFinancier));
    }

    @DeleteMapping("/dfc/{id}")
    public ResponseEntity<Void> supprimerDirecteurFinancier(@PathVariable Long id) {
        verifierRoleDRC(); // Vérifier que l'utilisateur est un DRC
        directeurFinancierService.supprimerDirecteurFinancier(id);
        return ResponseEntity.noContent().build();
    }

    // === Méthode pour vérifier que l'utilisateur est un DRC ===
    private void verifierRoleDRC() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DRC"))) {
            throw new SecurityException("Accès refusé. Seul un DRC peut effectuer cette action.");
        }
    }
}