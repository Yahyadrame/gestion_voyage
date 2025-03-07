package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.CandidatureDTO;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import com.uasz.gestion_voyages.Voyage.modele.CandidatureNouveau;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatureMapper {
    CandidatureMapper INSTANCE = Mappers.getMapper(CandidatureMapper.class);

    CandidatureDTO toDto(Candidature candidature);

    CandidatureNouveau toEntityNouveau(CandidatureDTO candidatureDTO);
    CandidatureAncien toEntityAncien(CandidatureDTO candidatureDTO);
}