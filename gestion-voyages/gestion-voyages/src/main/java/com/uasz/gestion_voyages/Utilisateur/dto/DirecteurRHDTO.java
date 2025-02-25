package com.uasz.gestion_voyages.Utilisateur.dto;

import com.uasz.gestion_voyages.Authentification.dto.UtilisateurDTO;
import lombok.Data;

@Data
public class DirecteurRHDTO extends UtilisateurDTO {
    private String departement;
}

