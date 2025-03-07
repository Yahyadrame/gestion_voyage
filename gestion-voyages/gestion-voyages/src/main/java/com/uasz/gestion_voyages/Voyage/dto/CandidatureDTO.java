package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CandidatureDTO {
    private Long id;
    private String lieu;
    private Date dateDepart;
    private Date dateRetour;
    private String statut;
    private Long enseignantId;
    private Long cohorteId;
}