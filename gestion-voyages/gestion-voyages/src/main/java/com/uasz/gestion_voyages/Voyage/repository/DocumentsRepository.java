package com.uasz.gestion_voyages.Voyage.repository;

import com.uasz.gestion_voyages.Voyage.modele.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long> {
}