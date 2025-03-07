package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.DocumentsDTO;
import com.uasz.gestion_voyages.Voyage.modele.Documents;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentsMapper {
    DocumentsMapper INSTANCE = Mappers.getMapper(DocumentsMapper.class);

    DocumentsDTO toDto(Documents documents);
    Documents toEntity(DocumentsDTO documentsDTO);
}