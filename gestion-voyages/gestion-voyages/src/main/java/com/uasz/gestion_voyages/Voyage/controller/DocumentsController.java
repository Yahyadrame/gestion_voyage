package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Documents;
import com.uasz.gestion_voyages.Voyage.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    @Autowired
    private DocumentsService documentsService;

    @GetMapping
    public ResponseEntity<List<Documents>> listerDocuments() {
        return ResponseEntity.ok(documentsService.listerDocuments());
    }

    @PostMapping
    public ResponseEntity<Documents> ajouterDocument(@RequestBody Documents document) {
        return ResponseEntity.ok(documentsService.ajouterDocument(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> obtenirDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentsService.obtenirDocument(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documents> modifierDocument(@PathVariable Long id, @RequestBody Documents document) {
        return ResponseEntity.ok(documentsService.modifierDocument(id, document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDocument(@PathVariable Long id) {
        documentsService.supprimerDocument(id);
        return ResponseEntity.noContent().build();
    }
}