package com.uasz.gestion_voyages.Authentification.service;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Authentification.repository.RoleRepository;
import com.uasz.gestion_voyages.Authentification.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Utilisateur validerUtilisateur(String email, String password) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            System.out.println("Utilisateur trouvé : " + utilisateur.getEmail());
            System.out.println("Mot de passe fourni : " + password);
            System.out.println("Mot de passe stocké : " + utilisateur.getMotDePasse());

            if (passwordEncoder.matches(password, utilisateur.getMotDePasse())) {
                System.out.println("Mot de passe valide");
                return utilisateur;
            } else {
                System.out.println("Mot de passe invalide");
            }
        } else {
            System.out.println("Aucun utilisateur trouvé avec l'email : " + email);
        }
        return null;
    }
    public Utilisateur ajouter_Utilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    public Role ajouter_Role(Role role) {
        roleRepository.save(role);
        return role;
    }

    public void ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
        Utilisateur user = utilisateurRepository.findUtilisateurByUsername(utilisateur.getUsername());
        Role profil = roleRepository.findRoleByRole(role.getRole());

        if (user.getRole() == null) {
            user.setRole(new ArrayList<>());  // ✅ Évite une NullPointerException
        }

        user.getRole().add(profil);
        utilisateurRepository.save(user);  // ✅ Enregistre l'utilisateur mis à jour en base
    }


    public Utilisateur rechercher_Utilisateur(String username) {
        return utilisateurRepository.findUtilisateurByUsername(username);
    }



}
