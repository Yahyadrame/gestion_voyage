package com.uasz.gestion_voyages.Authentification.controller;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Authentification.security.JwtUtil;
import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurFinancier;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRecherche;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    @PostMapping("/creer-utilisateur")
    public ResponseEntity<?> creerUtilisateur(@RequestBody Map<String, String> utilisateurData) {
        String nom = utilisateurData.get("nom");
        String prenom = utilisateurData.get("prenom");
        String email = utilisateurData.get("email");
        String username = utilisateurData.get("username");
        String password = utilisateurData.get("password");
        String telephone = utilisateurData.get("telephone");
        String role = utilisateurData.get("role");

        // Vérifier si l'utilisateur connecté est un DRC
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DRC"))) {
            return ResponseEntity.status(403).body(Map.of("success", false, "message", "Accès refusé."));
        }

        // Créer l'utilisateur en fonction du rôle
        Utilisateur utilisateur;
        switch (role) {
            case "ENSEIGNANT":
                utilisateur = new Enseignant();
                ((Enseignant) utilisateur).setSpecialite(utilisateurData.get("specialite"));
                break;
            case "DRH":
                utilisateur = new DirecteurRH();
                ((DirecteurRH) utilisateur).setDepartement(utilisateurData.get("departement"));
                break;
            case "DFC":
                utilisateur = new DirecteurFinancier();
                ((DirecteurFinancier) utilisateur).setBudget(Double.parseDouble(utilisateurData.get("budget")));
                break;
            case "DRC":
                utilisateur = new DirecteurRecherche();
                ((DirecteurRecherche) utilisateur).setDomaine(utilisateurData.get("domaine"));
                break;
            default:
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Rôle invalide."));
        }

        // Remplir les informations de base
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setUsername(username);
        utilisateur.setMotDePasse(passwordEncoder.encode(password));
        utilisateur.setTelephone(telephone);

        // Ajouter le rôle
        Role roleUtilisateur = new Role(role);
        utilisateur.setRole(List.of(roleUtilisateur));

        // Sauvegarder l'utilisateur
        utilisateurService.ajouter_Utilisateur(utilisateur);

        return ResponseEntity.ok(Map.of("success", true, "message", "Utilisateur créé avec succès."));
    }
}