package com.uasz.gestion_voyages.Utilisateur.service;

import com.uasz.gestion_voyages.Authentification.mapper.UtilisateurMapper;
import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurFinancierDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurFinancier;
import com.uasz.gestion_voyages.Utilisateur.repository.DirecteurFinancierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirecteurFinancierService {

    @Autowired
    private DirecteurFinancierRepository directeurFinancierRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    public List<DirecteurFinancierDTO> listerDirecteursFinanciers() {
        return directeurFinancierRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public DirecteurFinancierDTO ajouterDirecteurFinancier(DirecteurFinancier directeurFinancier) {
        return utilisateurMapper.toDto(directeurFinancierRepository.save(directeurFinancier));
    }

    public DirecteurFinancierDTO obtenirDirecteurFinancier(Long id) {
        return utilisateurMapper.toDto(directeurFinancierRepository.findById(id).orElseThrow());
    }

    public DirecteurFinancierDTO modifierDirecteurFinancier(Long id, DirecteurFinancier directeurFinancier) {
        directeurFinancier.setId(id);
        return utilisateurMapper.toDto(directeurFinancierRepository.save(directeurFinancier));
    }

    public void supprimerDirecteurFinancier(Long id) {
        directeurFinancierRepository.deleteById(id);
    }
}