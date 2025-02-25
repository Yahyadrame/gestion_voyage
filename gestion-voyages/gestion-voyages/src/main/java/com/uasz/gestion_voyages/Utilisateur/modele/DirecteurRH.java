package com.uasz.gestion_voyages.Utilisateur.modele;

import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("DRH")
public class DirecteurRH extends Utilisateur {
    private String departement;
}
