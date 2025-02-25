package com.uasz.gestion_voyages.Utilisateur.repository;

import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {}

