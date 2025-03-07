package com.uasz.gestion_voyages.Voyage.dto;

import lombok.Data;

@Data
public class DocumentsDTO {
    private Long id;
    private String nom;
    private String cheminFichier;
    private Long candidatureId;
}