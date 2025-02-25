package com.uasz.gestion_voyages.Authentification.repository;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    // Trouver un rôle par son nom
    Role findRoleByRole(String role);
}