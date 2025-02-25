package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.VoyageEtude;
import com.uasz.gestion_voyages.Voyage.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public List<VoyageEtude> listerVoyages() {
        return voyageRepository.findAll();
    }

    public VoyageEtude ajouterVoyage(VoyageEtude voyage) {
        return voyageRepository.save(voyage);
    }

    public VoyageEtude obtenirVoyage(Long id) {
        return voyageRepository.findById(id).orElseThrow();
    }

    public VoyageEtude modifierVoyage(Long id, VoyageEtude voyage) {
        voyage.setId(id);
        return voyageRepository.save(voyage);
    }

    public void supprimerVoyage(Long id) {
        voyageRepository.deleteById(id);
    }
}