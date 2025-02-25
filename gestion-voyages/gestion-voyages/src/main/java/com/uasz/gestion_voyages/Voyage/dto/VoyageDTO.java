package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;
import java.util.Date;

@Data
public class VoyageDTO {
    private Long id;
    private String lieu;
    private Date dateDepart;
    private Date dateRetour;
    private String objectif;
}