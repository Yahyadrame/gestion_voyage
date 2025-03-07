package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.Cohorte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CohorteRepository extends JpaRepository<Cohorte, Long> {
    List<Cohorte> findByEstOuverte(boolean estOuverte); // Trouver les cohortes ouvertes
}