package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Utilisateur.dto.EnseignantDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import com.uasz.gestion_voyages.Utilisateur.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignant")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    @GetMapping
    public ResponseEntity<List<EnseignantDTO>> listerEnseignants() {
        return ResponseEntity.ok(enseignantService.listerEnseignants());
    }



    @GetMapping("/{id}")
    public ResponseEntity<EnseignantDTO> obtenirEnseignant(@PathVariable Long id) {
        return ResponseEntity.ok(enseignantService.obtenirEnseignant(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnseignantDTO> modifierEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        return ResponseEntity.ok(enseignantService.modifierEnseignant(id, enseignant));
    }


}