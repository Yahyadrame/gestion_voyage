package com.uasz.gestion_voyages.Voyage.controller;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.modele.Documents;
import com.uasz.gestion_voyages.Voyage.service.CandidatureService;
import com.uasz.gestion_voyages.Voyage.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private DocumentsService documentsService;

    // Lister toutes les candidatures
    @GetMapping
    public ResponseEntity<List<Candidature>> listerCandidatures() {
        return ResponseEntity.ok(candidatureService.listerCandidatures());
    }

    // Soumettre une candidature pour un ancien enseignant
    @PostMapping("/ancien")
    public ResponseEntity<?> soumettreAncienCandidature(
            @RequestParam("enseignantId") Long enseignantId,
            @RequestParam("email") String email,
            @RequestParam("lieu") String lieu,
            @RequestParam("periode") String periode,
            @RequestParam("carteEmbarquement") MultipartFile carteEmbarquement,
            @RequestParam("destinationPrecedente") String destinationPrecedente,
            @RequestParam("dateDepartPrecedent") String dateDepartPrecedent,
            @RequestParam("dateRetourPrecedent") String dateRetourPrecedent,
            @RequestParam("rapportVoyagePrecedent") MultipartFile rapportVoyagePrecedent) {
        try {
            Candidature candidature = new Candidature();
            candidature.setEmail(email);
            candidature.setLieu(lieu);
            candidature.setPeriode(periode);
            candidature.setDestinationPrecedente(destinationPrecedente);
            candidature.setDateDepartPrecedent(dateDepartPrecedent);
            candidature.setDateRetourPrecedent(dateRetourPrecedent);

            // Enregistrer les fichiers dans Documents
            Documents carteEmbarquementDoc = documentsService.enregistrerDocument(carteEmbarquement);
            Documents rapportVoyageDoc = documentsService.enregistrerDocument(rapportVoyagePrecedent);

            // Associer les documents à la candidature
            candidature.getDocuments().add(carteEmbarquementDoc);
            candidature.getDocuments().add(rapportVoyageDoc);

            return ResponseEntity.ok(candidatureService.soumettreAncienCandidature(enseignantId, candidature));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Soumettre une candidature pour un nouveau enseignant
    @PostMapping("/nouveau")
    public ResponseEntity<?> soumettreNouveauCandidature(
            @RequestParam("enseignantId") Long enseignantId,
            @RequestParam("email") String email,
            @RequestParam("lieu") String lieu,
            @RequestParam("periode") String periode,
            @RequestParam("arreteTitularisation") MultipartFile arreteTitularisation) {
        try {
            Candidature candidature = new Candidature();
            candidature.setEmail(email);
            candidature.setLieu(lieu);
            candidature.setPeriode(periode);

            // Enregistrer le fichier dans Documents
            Documents arreteTitularisationDoc = documentsService.enregistrerDocument(arreteTitularisation);

            // Associer le document à la candidature
            candidature.getDocuments().add(arreteTitularisationDoc);

            return ResponseEntity.ok(candidatureService.soumettreNouveauCandidature(enseignantId, candidature));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtenir une candidature par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidature> obtenirCandidature(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.obtenirCandidature(id));
    }

    // Modifier une candidature
    @PutMapping("/{id}")
    public ResponseEntity<Candidature> modifierCandidature(@PathVariable Long id, @RequestBody Candidature candidature) {
        return ResponseEntity.ok(candidatureService.modifierCandidature(id, candidature));
    }

    // Supprimer une candidature
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCandidature(@PathVariable Long id) {
        candidatureService.supprimerCandidature(id);
        return ResponseEntity.noContent().build();
    }
}