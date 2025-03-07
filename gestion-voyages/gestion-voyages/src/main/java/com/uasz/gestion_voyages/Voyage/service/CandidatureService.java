package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureNouveau;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureRepository;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureNouveauRepository;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureAncienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    private final CandidatureRepository candidatureRepository;
    private final CandidatureNouveauRepository candidatureNouveauRepository;
    private final CandidatureAncienRepository candidatureAncienRepository;
    private final NotificationService notificationService;

    @Autowired
    public CandidatureService(CandidatureRepository candidatureRepository,
                              CandidatureNouveauRepository candidatureNouveauRepository,
                              CandidatureAncienRepository candidatureAncienRepository,
                              NotificationService notificationService) {
        this.candidatureRepository = candidatureRepository;
        this.candidatureNouveauRepository = candidatureNouveauRepository;
        this.candidatureAncienRepository = candidatureAncienRepository;
        this.notificationService = notificationService;
    }

    // Soumettre une candidature pour un nouvel enseignant
    public CandidatureNouveau soumettreCandidatureNouveau(CandidatureNouveau candidatureNouveau) {
        if (enseignantADejaVoyage(candidatureNouveau.getEnseignant().getId())) {
            throw new IllegalStateException("L'enseignant a déjà voyagé.");
        }
        return candidatureNouveauRepository.save(candidatureNouveau);
    }

    // Soumettre une candidature pour un ancien enseignant
    public CandidatureAncien soumettreCandidatureAncien(CandidatureAncien candidatureAncien) {
        return candidatureAncienRepository.save(candidatureAncien);
    }

    // Lister toutes les candidatures
    public List<Candidature> listerToutesCandidatures() {
        return candidatureRepository.findAll();
    }

    // Valider une candidature
    public Candidature validerCandidature(Long id) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
        candidature.setStatut("VALIDE");
        notificationService.envoyerNotification(
                candidature.getEnseignant().getEmail(),
                "Votre candidature a été validée."
        );
        return candidatureRepository.save(candidature);
    }

    // Rejeter une candidature
    public Candidature rejeterCandidature(Long id) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
        candidature.setStatut("REJETE");
        notificationService.envoyerNotification(
                candidature.getEnseignant().getEmail(),
                "Votre candidature a été rejetée."
        );
        return candidatureRepository.save(candidature);
    }

    // Vérifier si l'enseignant a déjà voyagé
    public boolean enseignantADejaVoyage(Long enseignantId) {
        List<Candidature> candidatures = candidatureRepository.findByEnseignantId(enseignantId);
        return candidatures.stream().anyMatch(c -> "VALIDE".equals(c.getStatut()));
    }
}