package com.uasz.gestion_voyages.Utilisateur.repository;

import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirecteurRHRepository extends JpaRepository<DirecteurRH, Long> {}

