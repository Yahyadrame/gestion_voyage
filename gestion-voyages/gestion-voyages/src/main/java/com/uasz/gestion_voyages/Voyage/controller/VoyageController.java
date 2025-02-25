package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.VoyageEtude;
import com.uasz.gestion_voyages.Voyage.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyage")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public ResponseEntity<List<VoyageEtude>> listerVoyages() {
        return ResponseEntity.ok(voyageService.listerVoyages());
    }

    @PostMapping
    public ResponseEntity<VoyageEtude> ajouterVoyage(@RequestBody VoyageEtude voyage) {
        return ResponseEntity.ok(voyageService.ajouterVoyage(voyage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoyageEtude> obtenirVoyage(@PathVariable Long id) {
        return ResponseEntity.ok(voyageService.obtenirVoyage(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoyageEtude> modifierVoyage(@PathVariable Long id, @RequestBody VoyageEtude voyage) {
        return ResponseEntity.ok(voyageService.modifierVoyage(id, voyage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVoyage(@PathVariable Long id) {
        voyageService.supprimerVoyage(id);
        return ResponseEntity.noContent().build();
    }
}