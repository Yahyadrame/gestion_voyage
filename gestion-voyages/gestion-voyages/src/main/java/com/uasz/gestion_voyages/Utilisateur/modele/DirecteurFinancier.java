package com.uasz.gestion_voyages.Utilisateur.modele;

import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("DFC")
public class DirecteurFinancier extends Utilisateur {
    private double budget;
}
