package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    public List<Candidature> listerCandidatures() {
        return candidatureRepository.findAll();
    }

    public Candidature ajouterCandidature(Candidature candidature) {
        // Valider les conditions avant de soumettre la candidature
        if (validerConditionsCandidature(candidature)) {
            return candidatureRepository.save(candidature);
        } else {
            throw new IllegalArgumentException("Les conditions pour soumettre une candidature ne sont pas remplies.");
        }
    }

    public Candidature obtenirCandidature(Long id) {
        return candidatureRepository.findById(id).orElseThrow();
    }

    public Candidature modifierCandidature(Long id, Candidature candidature) {
        candidature.setId(id);
        return candidatureRepository.save(candidature);
    }

    public void supprimerCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }

    private boolean validerConditionsCandidature(Candidature candidature) {
        Enseignant enseignant = candidature.getEnseignant();

        if (enseignant.aDejaVoyage()) {  // ✅ Vérifie si l'enseignant a déjà voyagé
            // Enseignant ancien → Vérifier les justificatifs du dernier voyage
            return candidature.getCarteEmbarquement() != null &&
                    candidature.getDestinationPrecedente() != null &&
                    candidature.getDateDepartPrecedent() != null &&
                    candidature.getDateRetourPrecedent() != null &&
                    candidature.getRapportVoyagePrecedent() != null;
        } else {
            // Enseignant nouveau → Il doit être titulaire avec un arrêté de titularisation
            return candidature.isTitulaire() && candidature.getArreteTitularisation() != null;
        }
    }

}