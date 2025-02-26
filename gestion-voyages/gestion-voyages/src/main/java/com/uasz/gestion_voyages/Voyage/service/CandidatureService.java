package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Utilisateur.dto.EnseignantDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import com.uasz.gestion_voyages.Utilisateur.repository.EnseignantRepository;
import com.uasz.gestion_voyages.Utilisateur.service.EnseignantService;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private EnseignantService enseignantService;

    // Lister toutes les candidatures
    public List<Candidature> listerCandidatures() {
        return candidatureRepository.findAll();
    }

    // Soumettre une candidature pour un ancien enseignant
    public Candidature soumettreAncienCandidature(Long enseignantId, Candidature candidature) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));

        EnseignantDTO enseignantDTO = enseignantService.obtenirEnseignant(enseignantId);

        if (!enseignantDTO.aDejaVoyage()) {
            throw new IllegalArgumentException("L'enseignant n'a pas encore voyagé.");
        }

        candidature.setEnseignant(enseignant);
        return candidatureRepository.save(candidature);
    }

    // Soumettre une candidature pour un nouveau enseignant
    public Candidature soumettreNouveauCandidature(Long enseignantId, Candidature candidature) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));

        EnseignantDTO enseignantDTO = enseignantService.obtenirEnseignant(enseignantId);

        if (enseignantDTO.aDejaVoyage()) {
            throw new IllegalArgumentException("L'enseignant a déjà voyagé.");
        }

        candidature.setEnseignant(enseignant);
        return candidatureRepository.save(candidature);
    }

    // Obtenir une candidature par son ID
    public Candidature obtenirCandidature(Long id) {
        return candidatureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidature non trouvée."));
    }

    // Modifier une candidature
    public Candidature modifierCandidature(Long id, Candidature candidature) {
        Candidature existingCandidature = obtenirCandidature(id);
        existingCandidature.setLieu(candidature.getLieu());
        existingCandidature.setPeriode(candidature.getPeriode());
        existingCandidature.setDestinationPrecedente(candidature.getDestinationPrecedente());
        existingCandidature.setDateDepartPrecedent(candidature.getDateDepartPrecedent());
        existingCandidature.setDateRetourPrecedent(candidature.getDateRetourPrecedent());
        return candidatureRepository.save(existingCandidature);
    }

    // Supprimer une candidature
    public void supprimerCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
}
