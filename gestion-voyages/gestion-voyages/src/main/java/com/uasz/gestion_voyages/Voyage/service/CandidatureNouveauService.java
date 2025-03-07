package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.CandidatureNouveau;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureNouveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatureNouveauService {

    @Autowired
    private CandidatureNouveauRepository candidatureNouveauRepository;

    // Soumettre une candidature pour un nouvel enseignant
    public CandidatureNouveau soumettreCandidatureNouveau(CandidatureNouveau candidatureNouveau) {
        return candidatureNouveauRepository.save(candidatureNouveau);
    }
}