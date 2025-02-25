package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CandidatureDTO {
    private Long id;
    private String statut;
    private Date dateSoumission;

    // Informations sur le voyage
    private String lieu;
    private Date dateDepart;
    private Date dateRetour;

    // Conditions pour les nouveaux enseignants
    private boolean titulaire;
    private String arreteTitularisation;

    // Conditions pour les anciens enseignants
    private String carteEmbarquement;
    private String destinationPrecedente;
    private Date dateDepartPrecedent;
    private Date dateRetourPrecedent;
    private String rapportVoyagePrecedent;

    private Long voyageId;
    private Long enseignantId;
}