package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CohorteDTO {
    private Long id;
    private String nom;
    private Date dateOuverture;
    private Date dateFermeture;
    private boolean estOuverte;
    private String statut;
}