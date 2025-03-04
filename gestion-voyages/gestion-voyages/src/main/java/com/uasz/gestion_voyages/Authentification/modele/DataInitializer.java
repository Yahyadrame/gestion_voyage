package com.uasz.gestion_voyages.Authentification.modele;

import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurService utilisateurService;

    @Autowired
    public DataInitializer(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer les rôles s'ils n'existent pas déjà
        Role roleEnseignant = utilisateurService.ajouter_Role(new Role("ENSEIGNANT"));
        Role roleDRH = utilisateurService.ajouter_Role(new Role("DRH"));
        Role roleDRC = utilisateurService.ajouter_Role(new Role("DRC"));
        Role roleDFC = utilisateurService.ajouter_Role(new Role("DFC"));
    }
}
