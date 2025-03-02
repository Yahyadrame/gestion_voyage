package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurFinancierDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurFinancier;
import com.uasz.gestion_voyages.Utilisateur.service.DirecteurFinancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dfc")
public class DirecteurFinancierController {

    @Autowired
    private DirecteurFinancierService directeurFinancierService;

    @GetMapping
    public ResponseEntity<List<DirecteurFinancierDTO>> listerDirecteursFinanciers() {
        return ResponseEntity.ok(directeurFinancierService.listerDirecteursFinanciers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DirecteurFinancierDTO> obtenirDirecteurFinancier(@PathVariable Long id) {
        return ResponseEntity.ok(directeurFinancierService.obtenirDirecteurFinancier(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirecteurFinancierDTO> modifierDirecteurFinancier(@PathVariable Long id, @RequestBody DirecteurFinancier directeurFinancier) {
        return ResponseEntity.ok(directeurFinancierService.modifierDirecteurFinancier(id, directeurFinancier));
    }


}