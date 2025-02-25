package com.uasz.gestion_voyages.Authentification.dto;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String login;
    private List<Role> role;
    private String telephone;
}
