package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
}