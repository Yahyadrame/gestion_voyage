package com.uasz.gestion_voyages.Utilisateur.controller;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import com.uasz.gestion_voyages.Authentification.modele.UtilisateurRequest;
import com.uasz.gestion_voyages.Authentification.service.EmailService;
import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import com.uasz.gestion_voyages.Utilisateur.dto.*;
import com.uasz.gestion_voyages.Utilisateur.modele.*;
import com.uasz.gestion_voyages.Utilisateur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drc")
public class DirecteurRechercheController {
    public static final Role ROLE_DRC = new Role("DRC");
    public static final Role ROLE_ENSEIGNANT = new Role("ENSEIGNANT");
    public static final Role ROLE_DRH = new Role("DRH");
    public static final Role ROLE_DFC = new Role("DFC");
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DirecteurRechercheService directeurRechercheService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DirecteurRHService directeurRHService;

    @Autowired
    private DirecteurFinancierService directeurFinancierService;

    // === Gestion des DRC ===
    @GetMapping
    public ResponseEntity<List<DirecteurRechercheDTO>> listerDirecteursRecherche() {
        return ResponseEntity.ok(directeurRechercheService.listerDirecteursRecherche());
    }

    @PostMapping
    public ResponseEntity<DirecteurRechercheDTO> ajouterDirecteurRecherche(@RequestBody DirecteurRecherche directeurRecherche) {
        return ResponseEntity.ok(directeurRechercheService.ajouterDirecteurRecherche(directeurRecherche));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<DirecteurRechercheDTO> obtenirDirecteurRecherche(@PathVariable Long id) {
        return ResponseEntity.ok(directeurRechercheService.obtenirDirecteurRecherche(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<DirecteurRechercheDTO> modifierDirecteurRecherche(@PathVariable Long id, @RequestBody DirecteurRecherche directeurRecherche) {
        return ResponseEntity.ok(directeurRechercheService.modifierDirecteurRecherche(id, directeurRecherche));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<Void> supprimerDirecteurRecherche(@PathVariable Long id) {
        directeurRechercheService.supprimerDirecteurRecherche(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des enseignants ===
    @PostMapping("/enseignants")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<EnseignantDTO> ajouterEnseignant(@RequestBody Enseignant enseignant) {
        return ResponseEntity.ok(enseignantService.ajouterEnseignant(enseignant));
    }

    @PutMapping("/enseignants/{id}")
    public ResponseEntity<EnseignantDTO> modifierEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        return ResponseEntity.ok(enseignantService.modifierEnseignant(id, enseignant));
    }

    @DeleteMapping("/enseignants/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<Void> supprimerEnseignant(@PathVariable Long id) {
        enseignantService.supprimerEnseignant(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DRH ===
    @PostMapping("/drh")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<DirecteurRHDTO> ajouterDirecteurRH(@RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.ajouterDirecteurRH(directeurRH));
    }

    @PutMapping("/drh/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<DirecteurRHDTO> modifierDirecteurRH(@PathVariable Long id, @RequestBody DirecteurRH directeurRH) {
        return ResponseEntity.ok(directeurRHService.modifierDirecteurRH(id, directeurRH));
    }

    @DeleteMapping("/drh/{id}")
    @PreAuthorize("hasAuthority('DRC')")
    public ResponseEntity<Void> supprimerDirecteurRH(@PathVariable Long id) {
        directeurRHService.supprimerDirecteurRH(id);
        return ResponseEntity.noContent().build();
    }

    // === Gestion des DFC ===
    @PostMapping("/dfc")
    public ResponseEntity<DirecteurFinancierDTO> ajouterDirecteurFinancier(@RequestBody DirecteurFinancier directeurFinancier) {
        return ResponseEntity.ok(directeurFinancierService.ajouterDirecteurFinancier(directeurFinancier));
    }

    @PutMapping("/dfc/{id}")
    public ResponseEntity<DirecteurFinancierDTO> modifierDirecteurFinancier(@PathVariable Long id, @RequestBody DirecteurFinancier directeurFinancier) {
        return ResponseEntity.ok(directeurFinancierService.modifierDirecteurFinancier(id, directeurFinancier));
    }

    @DeleteMapping("/dfc/{id}")
    public ResponseEntity<Void> supprimerDirecteurFinancier(@PathVariable Long id) {
        directeurFinancierService.supprimerDirecteurFinancier(id);
        return ResponseEntity.noContent().build();
    }

    // === Méthode pour vérifier que l'utilisateur est un DRC ===



    @PostMapping("/utilisateurs")
    public ResponseEntity<?> creerUtilisateur(@RequestBody UtilisateurRequest utilisateurRequest) {
        // Enregistrer le mot de passe chiffré en base de données
        String motDePasseCrypte = passwordEncoder.encode(utilisateurRequest.getMotDePasse());
        utilisateurRequest.setMotDePasse(motDePasseCrypte);

        // Envoyer le mot de passe en clair par e-mail
        String email = utilisateurRequest.getEmail();
        String username = utilisateurRequest.getUsername();
        String passwordEnClair = utilisateurRequest.getMotDePasseEnClair(); // Mot de passe en clair

        if (passwordEnClair == null || passwordEnClair.isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe en clair est requis pour l'envoi par e-mail.");
        }

        switch (utilisateurRequest.getRole()) {
            case "DRC":
                DirecteurRecherche directeurRecherche = utilisateurRequest.toDirecteurRecherche();
                DirecteurRechercheDTO directeurRecherche1 = directeurRechercheService.ajouterDirecteurRecherche(directeurRecherche);
                utilisateurService.ajouter_UtilisateurRoles(directeurRecherche, ROLE_DRC);
                envoyerIdentifiantsParEmail(email, username, passwordEnClair); // Envoyer le mot de passe en clair
                return ResponseEntity.ok(directeurRecherche);
            case "ENSEIGNANT":
                Enseignant enseignant = utilisateurRequest.toEnseignant();
                EnseignantDTO enseignant1 = enseignantService.ajouterEnseignant(enseignant);
                utilisateurService.ajouter_UtilisateurRoles(enseignant, ROLE_ENSEIGNANT);
                envoyerIdentifiantsParEmail(email, username, passwordEnClair); // Envoyer le mot de passe en clair
                return ResponseEntity.ok(enseignant);
            case "DRH":
                DirecteurRH directeurRH = utilisateurRequest.toDirecteurRH();
                DirecteurRHDTO directeurRH1 = directeurRHService.ajouterDirecteurRH(directeurRH);
                utilisateurService.ajouter_UtilisateurRoles(directeurRH, ROLE_DRH);
                envoyerIdentifiantsParEmail(email, username, passwordEnClair); // Envoyer le mot de passe en clair
                return ResponseEntity.ok(directeurRH);
            case "DFC":
                DirecteurFinancier directeurFinancier = utilisateurRequest.toDirecteurFinancier();
                DirecteurFinancierDTO directeurFinancier1 = directeurFinancierService.ajouterDirecteurFinancier(directeurFinancier);
                utilisateurService.ajouter_UtilisateurRoles(directeurFinancier, ROLE_DFC);
                envoyerIdentifiantsParEmail(email, username, passwordEnClair); // Envoyer le mot de passe en clair
                return ResponseEntity.ok(directeurFinancier);
            default:
                throw new IllegalArgumentException("Rôle non reconnu: " + utilisateurRequest.getRole());
        }
    }
    private void envoyerIdentifiantsParEmail(String email, String username, String passwordEnClair) {
        String subject = "Vos identifiants de connexion";
        String text = "Bonjour,\n\nVoici vos identifiants de connexion :\n\n"
                + "Nom d'utilisateur : " + email + "\n"
                + "Mot de passe : " + passwordEnClair + "\n\n"
                + "Cordialement,\nL'équipe de gestion des voyages\n\n"
                + "y.d31@zig.univ.sn  et m.d289@zig.univ.sn";

        emailService.envoyerEmail(email, subject, text);
    }
}