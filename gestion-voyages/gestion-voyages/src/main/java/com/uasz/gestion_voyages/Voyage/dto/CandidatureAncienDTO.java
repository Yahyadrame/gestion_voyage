package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CandidatureAncienDTO extends CandidatureDTO {
    private String rapportPrecedent; // Rapport du précédent voyage pour les anciens enseignants
}