package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping
    public ResponseEntity<List<Candidature>> listerCandidatures() {
        return ResponseEntity.ok(candidatureService.listerCandidatures());
    }

    @PostMapping
    public ResponseEntity<?> ajouterCandidature(@RequestBody Candidature candidature) {
        try {
            return ResponseEntity.ok(candidatureService.ajouterCandidature(candidature));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidature> obtenirCandidature(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.obtenirCandidature(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidature> modifierCandidature(@PathVariable Long id, @RequestBody Candidature candidature) {
        return ResponseEntity.ok(candidatureService.modifierCandidature(id, candidature));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCandidature(@PathVariable Long id) {
        candidatureService.supprimerCandidature(id);
        return ResponseEntity.noContent().build();
    }
}