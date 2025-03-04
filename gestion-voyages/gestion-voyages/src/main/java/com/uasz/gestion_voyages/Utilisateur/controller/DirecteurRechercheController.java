package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Authentification.modele.UtilisateurRequest;
import com.uasz.gestion_voyages.Utilisateur.dto.*;
import com.uasz.gestion_voyages.Utilisateur.modele.*;
import com.uasz.gestion_voyages.Utilisateur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drc")
public class DirecteurRechercheController {
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        return ResponseEntity.ok(enseignantService.ajouterEnseignant(enseignant));
    }

    @PutMapping("/enseignants/{id}")
    public ResponseEntity<EnseignantDTO> modifierEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        return ResponseEntity.ok(enseignantService.modifierEnseignant(id, enseignant));
    }

    @DeleteMapping("/enseignants/{id}")
    public ResponseEntity<Void> supprimerEnseignant(@PathVariable Long id) {
        enseignantService.supprimerEnseignant(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DRH ===
    @PostMapping("/drh")
    public ResponseEntity<DirecteurRHDTO> ajouterDirecteurRH(@RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.ajouterDirecteurRH(directeurRH));
    }

    @PutMapping("/drh/{id}")
    public ResponseEntity<DirecteurRHDTO> modifierDirecteurRH(@PathVariable Long id, @RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.modifierDirecteurRH(id, directeurRH));
    }

    @DeleteMapping("/drh/{id}")
    public ResponseEntity<Void> supprimerDirecteurRH(@PathVariable Long id) {
        directeurRHService.supprimerDirecteurRH(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DFC ===
    @PostMapping("/dfc")
    public ResponseEntity<DirecteurFinancierDTO> ajouterDirecteurFinancier(@RequestBody DirecteurFinancier directeurFinancier) {
        return ResponseEntity.ok(directeurFinancierService.ajouterDirecteurFinancier(directeurFinancier));
    }

    @PutMapping("/dfc/{id}")
    public ResponseEntity<DirecteurFinancierDTO> modifierDirecteurFinancier(@PathVariable Long id, @RequestBody DirecteurFinancier directeurFinancier) {
        return ResponseEntity.ok(directeurFinancierService.modifierDirecteurFinancier(id, directeurFinancier));
    }

    @DeleteMapping("/dfc/{id}")
    public ResponseEntity<Void> supprimerDirecteurFinancier(@PathVariable Long id) {
        directeurFinancierService.supprimerDirecteurFinancier(id);
        return ResponseEntity.noContent().build();
    }

    // === Méthode pour vérifier que l'utilisateur est un DRC ===


    @PostMapping("/utilisateurs")
    public ResponseEntity<?> creerUtilisateur(@RequestBody UtilisateurRequest utilisateurRequest) {

        String motDePasseCrypte = passwordEncoder.encode(utilisateurRequest.getMotDePasse());
        utilisateurRequest.setMotDePasse(motDePasseCrypte);

        switch (utilisateurRequest.getRole()) {
            case "DRC":
                DirecteurRecherche directeurRecherche = utilisateurRequest.toDirecteurRecherche();
                return ResponseEntity.ok(directeurRechercheService.ajouterDirecteurRecherche(directeurRecherche));
            case "ENSEIGNANT":
                Enseignant enseignant = utilisateurRequest.toEnseignant();
                return ResponseEntity.ok(enseignantService.ajouterEnseignant(enseignant));
            case "DRH":
                DirecteurRH directeurRH = utilisateurRequest.toDirecteurRH();
                return ResponseEntity.ok(directeurRHService.ajouterDirecteurRH(directeurRH));
            case "DFC":
                DirecteurFinancier directeurFinancier = utilisateurRequest.toDirecteurFinancier();
                return ResponseEntity.ok(directeurFinancierService.ajouterDirecteurFinancier(directeurFinancier));
            default:
                throw new IllegalArgumentException("Rôle non reconnu: " + utilisateurRequest.getRole());
        }
    }
}