package com.uasz.gestion_voyages.Authentification.service;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Authentification.repository.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Tentative de connexion avec email : " + email); // Ajout du log

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        String[] roles = utilisateur.getRole().stream()
                .map(role -> "ROLE_" + role.getRole())
                .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .roles(roles)
                .build();
    }

}