package com.uasz.gestion_voyages.Utilisateur.service;

import com.uasz.gestion_voyages.Authentification.mapper.UtilisateurMapper;
import com.uasz.gestion_voyages.Utilisateur.dto.DirecteurRechercheDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRecherche;
import com.uasz.gestion_voyages.Utilisateur.repository.DirecteurRechercheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirecteurRechercheService {

    @Autowired
    private DirecteurRechercheRepository directeurRechercheRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    public List<DirecteurRechercheDTO> listerDirecteursRecherche() {
        return directeurRechercheRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public DirecteurRechercheDTO ajouterDirecteurRecherche(DirecteurRecherche directeurRecherche) {
        return utilisateurMapper.toDto(directeurRechercheRepository.save(directeurRecherche));
    }

    public DirecteurRechercheDTO obtenirDirecteurRecherche(Long id) {
        return utilisateurMapper.toDto(directeurRechercheRepository.findById(id).orElseThrow());
    }

    public DirecteurRechercheDTO modifierDirecteurRecherche(Long id, DirecteurRecherche directeurRecherche) {
        directeurRecherche.setId(id);
        return utilisateurMapper.toDto(directeurRechercheRepository.save(directeurRecherche));
    }

    public void supprimerDirecteurRecherche(Long id) {
        directeurRechercheRepository.deleteById(id);
    }
}