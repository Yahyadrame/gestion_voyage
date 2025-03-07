package com.uasz.gestion_voyages.Voyage.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class CandidatureNouveau extends Candidature {
    private String arreteTitularisation; // Arrêté de titularisation pour les nouveaux enseignants
}