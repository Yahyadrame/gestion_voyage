package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import com.uasz.gestion_voyages.Voyage.repository.CandidatureAncienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatureAncienService {

    @Autowired
    private CandidatureAncienRepository candidatureAncienRepository;

    // Soumettre une candidature pour un ancien enseignant
    public CandidatureAncien soumettreCandidatureAncien(CandidatureAncien candidatureAncien) {
        return candidatureAncienRepository.save(candidatureAncien);
    }
}