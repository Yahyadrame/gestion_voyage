package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.CandidatureAncien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureAncienRepository extends JpaRepository<CandidatureAncien, Long> {
}