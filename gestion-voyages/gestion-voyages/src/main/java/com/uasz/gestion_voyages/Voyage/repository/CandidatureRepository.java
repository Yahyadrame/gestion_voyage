package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByCohorteId(Long cohorteId); // Trouver les candidatures par cohorte
    List<Candidature> findByEnseignantId(Long enseignantId); // Trouver les candidatures par enseignant
}