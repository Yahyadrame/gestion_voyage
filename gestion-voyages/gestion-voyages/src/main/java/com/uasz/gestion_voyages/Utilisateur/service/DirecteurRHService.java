package com.uasz.gestion_voyages.Utilisateur.service;

import com.uasz.gestion_voyages.Authentification.mapper.UtilisateurMapper;
import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurRHDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import com.uasz.gestion_voyages.Utilisateur.repository.DirecteurRHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirecteurRHService {

    @Autowired
    private DirecteurRHRepository directeurRHRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    public List<DirecteurRHDTO> listerDirecteursRH() {
        return directeurRHRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public DirecteurRHDTO ajouterDirecteurRH(DirecteurRH directeurRH) {
        return utilisateurMapper.toDto(directeurRHRepository.save(directeurRH));
    }

    public DirecteurRHDTO obtenirDirecteurRH(Long id) {
        return utilisateurMapper.toDto(directeurRHRepository.findById(id).orElseThrow());
    }

    public DirecteurRHDTO modifierDirecteurRH(Long id, DirecteurRH directeurRH) {
        directeurRH.setId(id);
        return utilisateurMapper.toDto(directeurRHRepository.save(directeurRH));
    }

    public void supprimerDirecteurRH(Long id) {
        directeurRHRepository.deleteById(id);
    }
}