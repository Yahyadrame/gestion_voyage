package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.CandidatureDTO;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import org.springframework.stereotype.Component;

@Component
public class CandidatureMapper {

    public CandidatureDTO toDto(Candidature candidature) {
        CandidatureDTO dto = new CandidatureDTO();
        dto.setId(candidature.getId());
        dto.setStatut(candidature.getStatut());
        dto.setDateSoumission(candidature.getDateSoumission());
        dto.setLieu(candidature.getLieu());
        dto.setDateDepart(candidature.getDateDepart());
        dto.setDateRetour(candidature.getDateRetour());
        dto.setTitulaire(candidature.isTitulaire());
        dto.setArreteTitularisation(candidature.getArreteTitularisation());
        dto.setCarteEmbarquement(candidature.getCarteEmbarquement());
        dto.setDestinationPrecedente(candidature.getDestinationPrecedente());
        dto.setDateDepartPrecedent(candidature.getDateDepartPrecedent());
        dto.setDateRetourPrecedent(candidature.getDateRetourPrecedent());
        dto.setRapportVoyagePrecedent(candidature.getRapportVoyagePrecedent());
        dto.setVoyageId(candidature.getVoyage().getId());
        dto.setEnseignantId(candidature.getEnseignant().getId());
        return dto;
    }

    public Candidature toEntity(CandidatureDTO dto) {
        Candidature candidature = new Candidature();
        candidature.setId(dto.getId());
        candidature.setStatut(dto.getStatut());
        candidature.setDateSoumission(dto.getDateSoumission());
        candidature.setLieu(dto.getLieu());
        candidature.setDateDepart(dto.getDateDepart());
        candidature.setDateRetour(dto.getDateRetour());
        candidature.setTitulaire(dto.isTitulaire());
        candidature.setArreteTitularisation(dto.getArreteTitularisation());
        candidature.setCarteEmbarquement(dto.getCarteEmbarquement());
        candidature.setDestinationPrecedente(dto.getDestinationPrecedente());
        candidature.setDateDepartPrecedent(dto.getDateDepartPrecedent());
        candidature.setDateRetourPrecedent(dto.getDateRetourPrecedent());
        candidature.setRapportVoyagePrecedent(dto.getRapportVoyagePrecedent());
        // Note: Vous devrez injecter les services pour récupérer les entités VoyageEtude et Enseignant
        return candidature;
    }
}