package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.Cohorte;
import com.uasz.gestion_voyages.Voyage.repository.CohorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CohorteService {

    @Autowired
    private CohorteRepository cohorteRepository;

    // Ouvrir une nouvelle cohorte
    public Cohorte ouvrirCohorte(String nom, Date dateOuverture, Date dateFermeture) {
        Cohorte cohorte = new Cohorte();
        cohorte.setNom(nom);
        cohorte.setDateOuverture(dateOuverture);
        cohorte.setDateFermeture(dateFermeture);
        cohorte.setEstOuverte(true);
        return cohorteRepository.save(cohorte);
    }

    // Fermer une cohorte
    public Cohorte fermerCohorte(Long id) {
        Cohorte cohorte = cohorteRepository.findById(id).orElseThrow();
        cohorte.setEstOuverte(false);
        return cohorteRepository.save(cohorte);
    }

    // Lister les cohortes ouvertes
    public List<Cohorte> listerCohortesOuvertes() {
        return cohorteRepository.findByEstOuverte(true);
    }
}