package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurRechercheDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRecherche;
import com.uasz.gestion_voyages.Utilisateur.service.DirecteurRechercheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drc")
public class DirecteurRechercheController {

    @Autowired
    private DirecteurRechercheService directeurRechercheService;

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
}