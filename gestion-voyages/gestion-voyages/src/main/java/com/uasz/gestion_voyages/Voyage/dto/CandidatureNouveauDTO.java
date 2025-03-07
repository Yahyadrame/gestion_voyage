package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CandidatureNouveauDTO extends CandidatureDTO {
    private String arreteTitularisation; // Arrêté de titularisation pour les nouveaux enseignants
}