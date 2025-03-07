package com.uasz.gestion_voyages.Voyage.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CandidatureAncien extends Candidature {
    private String rapportPrecedent; // Rapport du précédent voyage pour les anciens enseignants
}