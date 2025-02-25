package com.uasz.gestion_voyages.Authentification.mapper;

import com.uasz.gestion_voyages.Authentification.dto.UtilisateurDTO;
import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Utilisateur.dto.*;
import com.uasz.gestion_voyages.Utilisateur.modele.*;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurDTO toDto(Utilisateur utilisateur) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setUsername(utilisateur.getUsername());
        dto.setEmail(utilisateur.getEmail());
        dto.setRole(utilisateur.getRole());
        dto.setTelephone(utilisateur.getTelephone());
        return dto;
    }

    public EnseignantDTO toDto(Enseignant enseignant) {
        EnseignantDTO dto = new EnseignantDTO();
        dto.setId(enseignant.getId());
        dto.setNom(enseignant.getNom());
        dto.setPrenom(enseignant.getPrenom());
        dto.setUsername(enseignant.getUsername());
        dto.setEmail(enseignant.getEmail());
        dto.setRole(enseignant.getRole());
        dto.setTelephone(enseignant.getTelephone());
        dto.setSpecialite(enseignant.getSpecialite());
        return dto;
    }

    public DirecteurRechercheDTO toDto(DirecteurRecherche drc) {
        DirecteurRechercheDTO dto = new DirecteurRechercheDTO();
        dto.setId(drc.getId());
        dto.setNom(drc.getNom());
        dto.setPrenom(drc.getPrenom());
        dto.setUsername(drc.getUsername());
        dto.setEmail(drc.getEmail());
        dto.setRole(drc.getRole());
        dto.setTelephone(drc.getTelephone());
        dto.setDomaine(drc.getDomaine());
        return dto;
    }

    public DirecteurRHDTO toDto(DirecteurRH drh) {
        DirecteurRHDTO dto = new DirecteurRHDTO();
        dto.setId(drh.getId());
        dto.setNom(drh.getNom());
        dto.setPrenom(drh.getPrenom());
        dto.setUsername(drh.getUsername());
        dto.setEmail(drh.getEmail());
        dto.setRole(drh.getRole());
        dto.setTelephone(drh.getTelephone());
        dto.setDepartement(drh.getDepartement());
        return dto;
    }

    public DirecteurFinancierDTO toDto(DirecteurFinancier dfc) {
        DirecteurFinancierDTO dto = new DirecteurFinancierDTO();
        dto.setId(dfc.getId());
        dto.setNom(dfc.getNom());
        dto.setPrenom(dfc.getPrenom());
        dto.setUsername(dfc.getUsername());
        dto.setEmail(dfc.getEmail());
        dto.setRole(dfc.getRole());
        dto.setTelephone(dfc.getTelephone());
        dto.setBudget(dfc.getBudget());
        return dto;
    }
}