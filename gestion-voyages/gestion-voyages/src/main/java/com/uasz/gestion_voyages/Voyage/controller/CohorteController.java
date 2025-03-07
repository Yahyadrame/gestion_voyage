package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Cohorte;
import com.uasz.gestion_voyages.Voyage.service.CohorteService;
import com.uasz.gestion_voyages.Voyage.repository.CohorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cohortes")
public class CohorteController {

    @Autowired
    private CohorteService cohorteService;

    @Autowired
    private CohorteRepository cohorteRepository;

    // Ouvrir une nouvelle cohorte (Seul le DRC peut le faire)
    @PostMapping("/ouvrir")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Cohorte> ouvrirCohorte(@RequestParam String nom, @RequestParam Date dateOuverture, @RequestParam Date dateFermeture) {
        Cohorte cohorte = new Cohorte();
        cohorte.setNom(nom);
        cohorte.setDateOuverture(dateOuverture);
        cohorte.setDateFermeture(dateFermeture);
        cohorte.setEstOuverte(true);
        cohorte.setStatut("OUVERTE"); // Statut initial
        return ResponseEntity.ok(cohorteRepository.save(cohorte));
    }

    // Fermer une cohorte (Seul le DRC peut le faire)
    @PutMapping("/{id}/fermer")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Cohorte> fermerCohorte(@PathVariable Long id) {
        Cohorte cohorte = cohorteService.fermerCohorte(id);
        return ResponseEntity.ok(cohorte);
    }

    // Semi-clôturer une cohorte (Seul le DRC peut le faire)
    @PutMapping("/{id}/semi-cloture")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Cohorte> semiCloturerCohorte(@PathVariable Long id) {
        Cohorte cohorte = cohorteRepository.findById(id).orElseThrow();
        cohorte.setStatut("SEMI_CLOTURE");
        return ResponseEntity.ok(cohorteRepository.save(cohorte));
    }

    // Clôturer définitivement une cohorte (Seul le DRC peut le faire)
    @PutMapping("/{id}/cloture-definitive")
    @PreAuthorize("hasRole('DRC')")
    public ResponseEntity<Cohorte> cloturerDefinitivement(@PathVariable Long id) {
        Cohorte cohorte = cohorteRepository.findById(id).orElseThrow();
        cohorte.setStatut("CLOTURE_DEFINITIVE");
        cohorte.setEstOuverte(false); // La période est fermée
        return ResponseEntity.ok(cohorteRepository.save(cohorte));
    }

    // Lister les cohortes ouvertes (Accessible à tous les utilisateurs authentifiés)
    @GetMapping("/ouvertes")
    public ResponseEntity<List<Cohorte>> listerCohortesOuvertes() {
        List<Cohorte> cohortes = cohorteService.listerCohortesOuvertes();
        return ResponseEntity.ok(cohortes);
    }
}
