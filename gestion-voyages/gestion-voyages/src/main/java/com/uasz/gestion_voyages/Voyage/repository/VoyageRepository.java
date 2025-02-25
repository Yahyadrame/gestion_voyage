package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.VoyageEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageRepository extends JpaRepository<VoyageEtude, Long> {
}