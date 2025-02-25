package com.uasz.gestion_voyages.Utilisateur.dto;

import com.uasz.gestion_voyages.Authentification.dto.UtilisateurDTO;
import lombok.Data;

@Data
public class EnseignantDTO extends UtilisateurDTO {
    private String specialite;
}
