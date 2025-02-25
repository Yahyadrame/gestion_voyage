package com.uasz.gestion_voyages.Voyage.mapper;

import com.uasz.gestion_voyages.Voyage.dto.VoyageDTO;
import com.uasz.gestion_voyages.Voyage.modele.VoyageEtude;
import org.springframework.stereotype.Component;

@Component
public class VoyageMapper {

    public VoyageDTO toDto(VoyageEtude voyage) {
        VoyageDTO dto = new VoyageDTO();
        dto.setId(voyage.getId());
        dto.setLieu(voyage.getLieu());
        dto.setDateDepart(voyage.getDateDepart());
        dto.setDateRetour(voyage.getDateRetour());
        dto.setObjectif(voyage.getObjectif());
        return dto;
    }

    public VoyageEtude toEntity(VoyageDTO dto) {
        VoyageEtude voyage = new VoyageEtude();
        voyage.setId(dto.getId());
        voyage.setLieu(dto.getLieu());
        voyage.setDateDepart(dto.getDateDepart());
        voyage.setDateRetour(dto.getDateRetour());
        voyage.setObjectif(dto.getObjectif());
        return voyage;
    }
}