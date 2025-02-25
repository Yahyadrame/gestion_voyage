package com.uasz.gestion_voyages.Utilisateur.service;

import com.uasz.gestion_voyages.Authentification.mapper.UtilisateurMapper;
import com.uasz.gestion_voyages.Utilisateur.dto.EnseignantDTO;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import com.uasz.gestion_voyages.Utilisateur.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    public List<EnseignantDTO> listerEnseignants() {
        return enseignantRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public EnseignantDTO ajouterEnseignant(Enseignant enseignant) {
        return utilisateurMapper.toDto(enseignantRepository.save(enseignant));
    }

    public EnseignantDTO obtenirEnseignant(Long id) {
        return utilisateurMapper.toDto(enseignantRepository.findById(id).orElseThrow());
    }

    public EnseignantDTO modifierEnseignant(Long id, Enseignant enseignant) {
        enseignant.setId(id);
        return utilisateurMapper.toDto(enseignantRepository.save(enseignant));
    }

    public void supprimerEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }
}