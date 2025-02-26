package com.uasz.gestion_voyages.Utilisateur.mapper;

import com.uasz.gestion_voyages.Utilisateur.dto.EnseignantDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    // Convertir Enseignant en EnseignantDTO
    EnseignantDTO toDto(Enseignant enseignant);

    // Convertir EnseignantDTO en Enseignant
    Enseignant toEntity(EnseignantDTO enseignantDTO);
}