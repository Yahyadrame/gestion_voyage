package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Documents;
import com.uasz.gestion_voyages.Voyage.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    @Autowired
    private DocumentsService documentsService;

    // Ajouter un document (Seul l'enseignant peut le faire)
    @PostMapping
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<Documents> ajouterDocument(@RequestBody Documents document) {
        Documents nouveauDocument = documentsService.ajouterDocument(document);
        return ResponseEntity.ok(nouveauDocument);
    }

    // Lister les documents par candidature (Seul l'enseignant ou le DRC peut le faire)
    @GetMapping("/candidature/{candidatureId}")
    @PreAuthorize("hasAnyRole('ENSEIGNANT', 'DRC')")
    public ResponseEntity<List<Documents>> listerDocumentsParCandidature(@PathVariable Long candidatureId) {
        List<Documents> documents = documentsService.listerDocumentsParCandidature(candidatureId);
        return ResponseEntity.ok(documents);
    }

    // Supprimer un document (Seul l'enseignant peut le faire)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<Void> supprimerDocument(@PathVariable Long id) {
        documentsService.supprimerDocument(id);
        return ResponseEntity.noContent().build();
    }
}