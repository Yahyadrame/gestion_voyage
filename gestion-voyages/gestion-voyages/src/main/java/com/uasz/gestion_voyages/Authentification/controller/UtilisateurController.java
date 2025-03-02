package com.uasz.gestion_voyages.Authentification.controller;

import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Authentification.security.JwtUtil;
import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Utilisateur utilisateur = utilisateurService.validerUtilisateur(email, password);

        if (utilisateur != null) {
            // Vérifier si l'utilisateur a des rôles
            if (utilisateur.getRole() == null || utilisateur.getRole().isEmpty()) {
                return ResponseEntity.status(403).body(Map.of("success", false, "message", "L'utilisateur n'a aucun rôle assigné."));
            }

            // Générer un token JWT
            String token = jwtUtil.generateToken(utilisateur.getUsername(), utilisateur.getRole().get(0).getRole());

            // Déterminer la redirection en fonction du rôle
            String redirection = determinerRedirection(utilisateur.getRole().get(0).getRole());

            // Retourner le token et la redirection dans la réponse
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "role", utilisateur.getRole().get(0).getRole(),
                    "token", token,
                    "redirection", redirection
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Identifiants invalides"));
        }
    }

    private String determinerRedirection(String role) {
        switch (role) {
            case "ENSEIGNANT":
                return "/enseignant/dashboard"; // Redirection vers le tableau de bord des enseignants
            case "DRH":
                return "/drh/dashboard"; // Redirection vers le tableau de bord des DRH
            case "DRC":
                return "/drc/dashboard"; // Redirection vers le tableau de bord des DRC
            case "DFC":
                return "/dfc/dashboard"; // Redirection vers le tableau de bord des DFC
            default:
                return "/"; // Redirection par défaut (page d'accueil)
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Déconnexion réussie");
    }


}