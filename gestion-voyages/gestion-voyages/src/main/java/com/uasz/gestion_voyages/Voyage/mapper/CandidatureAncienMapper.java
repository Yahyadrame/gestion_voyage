package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.CandidatureAncienDTO;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatureAncienMapper {
    CandidatureAncienMapper INSTANCE = Mappers.getMapper(CandidatureAncienMapper.class);

    CandidatureAncienDTO toDto(CandidatureAncien candidatureAncien);
    CandidatureAncien toEntity(CandidatureAncienDTO candidatureAncienDTO);
}