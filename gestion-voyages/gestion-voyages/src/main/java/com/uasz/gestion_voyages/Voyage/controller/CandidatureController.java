package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureNouveau;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import com.uasz.gestion_voyages.Voyage.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    // Soumettre une candidature pour un nouvel enseignant (Seul l'enseignant peut le faire)
    @PostMapping("/nouveau")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<CandidatureNouveau> soumettreCandidatureNouveau(@RequestBody CandidatureNouveau candidatureNouveau) {
        CandidatureNouveau nouvelleCandidature = candidatureService.soumettreCandidatureNouveau(candidatureNouveau);
        return ResponseEntity.ok(nouvelleCandidature);
    }

    // Soumettre une candidature pour un ancien enseignant (Seul l'enseignant peut le faire)
    @PostMapping("/ancien")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<CandidatureAncien> soumettreCandidatureAncien(@RequestBody CandidatureAncien candidatureAncien) {
        CandidatureAncien nouvelleCandidature = candidatureService.soumettreCandidatureAncien(candidatureAncien);
        return ResponseEntity.ok(nouvelleCandidature);
    }

    // Lister toutes les candidatures (Seul le DRC peut le faire)
    @GetMapping
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<List<Candidature>> listerToutesCandidatures() {
        List<Candidature> candidatures = candidatureService.listerToutesCandidatures();
        return ResponseEntity.ok(candidatures);
    }

    // Valider une candidature (Seul le DRC peut le faire)
    @PutMapping("/{id}/valider")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Candidature> validerCandidature(@PathVariable Long id) {
        Candidature candidature = candidatureService.validerCandidature(id);
        return ResponseEntity.ok(candidature);
    }

    // Rejeter une candidature (Seul le DRC peut le faire)
    @PutMapping("/{id}/rejeter")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Candidature> rejeterCandidature(@PathVariable Long id) {
        Candidature candidature = candidatureService.rejeterCandidature(id);
        return ResponseEntity.ok(candidature);
    }
}