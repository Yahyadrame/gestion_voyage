package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurRHDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import com.uasz.gestion_voyages.Utilisateur.service.DirecteurRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drh")
public class DirecteurRHController {

    @Autowired
    private DirecteurRHService directeurRHService;

    @GetMapping
    public ResponseEntity<List<DirecteurRHDTO>> listerDirecteursRH() {
        return ResponseEntity.ok(directeurRHService.listerDirecteursRH());
    }

    @PostMapping
    public ResponseEntity<DirecteurRHDTO> ajouterDirecteurRH(@RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.ajouterDirecteurRH(directeurRH));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirecteurRHDTO> obtenirDirecteurRH(@PathVariable Long id) {
        return ResponseEntity.ok(directeurRHService.obtenirDirecteurRH(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirecteurRHDTO> modifierDirecteurRH(@PathVariable Long id, @RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.modifierDirecteurRH(id, directeurRH));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDirecteurRH(@PathVariable Long id) {
        directeurRHService.supprimerDirecteurRH(id);
        return ResponseEntity.noContent().build();
    }
}