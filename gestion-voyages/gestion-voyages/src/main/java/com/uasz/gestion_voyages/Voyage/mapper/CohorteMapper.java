package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.CohorteDTO;
import com.uasz.gestion_voyages.Voyage.modele.Cohorte;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CohorteMapper {
    CohorteMapper INSTANCE = Mappers.getMapper(CohorteMapper.class);

    CohorteDTO toDto(Cohorte cohorte);
    Cohorte toEntity(CohorteDTO cohorteDTO);
}