package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.CandidatureNouveauDTO;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureNouveau;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatureNouveauMapper {
    CandidatureNouveauMapper INSTANCE = Mappers.getMapper(CandidatureNouveauMapper.class);

    CandidatureNouveauDTO toDto(CandidatureNouveau candidatureNouveau);
    CandidatureNouveau toEntity(CandidatureNouveauDTO candidatureNouveauDTO);
}