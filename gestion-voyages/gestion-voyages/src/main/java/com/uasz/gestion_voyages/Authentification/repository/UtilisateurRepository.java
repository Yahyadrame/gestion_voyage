package com.uasz.gestion_voyages.Authentification.repository;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Trouver un utilisateur par son email
    Optional<Utilisateur> findByEmail(String email);

    // Trouver un utilisateur par son username
    Utilisateur findUtilisateurByUsername(String username);

}