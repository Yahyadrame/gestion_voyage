package com.uasz.gestion_voyages;

import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uasz.gestion_voyages.Authentification.modele.Role;
import com.uasz.gestion_voyages.Authentification.service.UtilisateurService;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRecherche;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurFinancier;
import com.uasz.gestion_voyages.Utilisateur.service.EnseignantService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GestionVoyagesApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestionVoyagesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Ajouter les rôles
		Role roleEnseignant = utilisateurService.ajouter_Role(new Role("ENSEIGNANT"));
		Role roleDRH = utilisateurService.ajouter_Role(new Role("DRH"));
		Role roleDRC = utilisateurService.ajouter_Role(new Role("DRC"));
		Role roleDFC = utilisateurService.ajouter_Role(new Role("DFC"));
/*
		// Mot de passe crypté
		String password = passwordEncoder.encode("Passer123");

		// Ajouter un directeur RH
		DirecteurRH directeurRH1 = new DirecteurRH();
		directeurRH1.setNom("Ndiaye");
		directeurRH1.setPrenom("Fatou");
		directeurRH1.setEmail("fatou.ndiaye@example.com");
		directeurRH1.setUsername("fatoundiaye");
		directeurRH1.setMotDePasse(password);
		directeurRH1.setTelephone("771234568");
		directeurRH1.setDepartement("Ressources Humaines");
		utilisateurService.ajouter_Utilisateur(directeurRH1);
		utilisateurService.ajouter_UtilisateurRoles(directeurRH1, roleDRH);

		// Ajouter un directeur recherche
		DirecteurRecherche directeurRecherche1 = new DirecteurRecherche();
		directeurRecherche1.setNom("Sow");
		directeurRecherche1.setPrenom("Mamadou");
		directeurRecherche1.setEmail("mamadou.sow@example.com");
		directeurRecherche1.setUsername("mamadousow");
		directeurRecherche1.setMotDePasse(password);
		directeurRecherche1.setTelephone("771234569");
		directeurRecherche1.setDomaine("Recherche en Informatique");
		utilisateurService.ajouter_Utilisateur(directeurRecherche1);
		utilisateurService.ajouter_UtilisateurRoles(directeurRecherche1, roleDRC);

		// Ajouter un directeur financier
		DirecteurFinancier directeurFinancier1 = new DirecteurFinancier();
		directeurFinancier1.setNom("Gueye");
		directeurFinancier1.setPrenom("Ousmane");
		directeurFinancier1.setEmail("ousmane.gueye@example.com");
		directeurFinancier1.setUsername("ousmanegueye");
		directeurFinancier1.setMotDePasse(password);
		directeurFinancier1.setTelephone("771234570");
		directeurFinancier1.setBudget(1000000.00);
		utilisateurService.ajouter_Utilisateur(directeurFinancier1);
		utilisateurService.ajouter_UtilisateurRoles(directeurFinancier1, roleDFC);


		// Ajouter un enseignant
		Enseignant enseignant1 = new Enseignant();
		enseignant1.setNom("Diop");
		enseignant1.setPrenom("Aminata");
		enseignant1.setEmail("aminata.diop@example.com");
		enseignant1.setUsername("aminatadiop");
		enseignant1.setMotDePasse(password);
		enseignant1.setTelephone("771234567");
		enseignant1.setSpecialite("Mathématiques");

		// Condition : Cet enseignant doit-il être "ancien" ou "nouveau" ?
		// Par défaut, on dit qu’il est ancien s’il a déjà voyagé (exemple : true)
		boolean estAncien = true;  // ⚠️ Change à `false` pour un enseignant nouveau

		if (estAncien) {
			// ✅ Créer une candidature pour marquer l’enseignant comme "ancien"
			Candidature candidature1 = new Candidature();
			candidature1.setEnseignant(enseignant1);
			candidature1.setDateDepartPrecedent(new Date(2023 - 1900, 5, 15)); // ✅ Départ en juin 2023
			candidature1.setDateRetourPrecedent(new Date(2023 - 1900, 5, 25)); // ✅ Retour en juin 2023
			candidature1.setDestinationPrecedente("Paris");
			candidature1.setRapportVoyagePrecedent("Rapport de voyage complet.");
			candidature1.setCarteEmbarquement("Carte d’embarquement scannée.");

			// Associer la candidature à l'enseignant
			List<Candidature> candidatures = new ArrayList<>();
			candidatures.add(candidature1);
			enseignant1.setCandidatures(candidatures);
		}

		utilisateurService.ajouter_Utilisateur(enseignant1);
		utilisateurService.ajouter_UtilisateurRoles(enseignant1, roleEnseignant);



		// Ajouter un enseignant "nouveau"
		Enseignant enseignant2 = new Enseignant();
		enseignant2.setNom("Fall");
		enseignant2.setPrenom("Ibrahima");
		enseignant2.setEmail("ibrahima.fall@example.com");
		enseignant2.setUsername("ibrahimafall");
		enseignant2.setMotDePasse(password);
		enseignant2.setTelephone("771234568");
		enseignant2.setSpecialite("Physique");

		// ✅ L’enseignant est "nouveau", donc il n'a pas de candidature
		// Mais il doit être titulaire avec un arrêté de titularisation
		Candidature candidature2 = new Candidature();
		candidature2.setEnseignant(enseignant2);
		candidature2.setTitulaire(true);  // ✅ Il doit être titulaire
		candidature2.setArreteTitularisation("Arreté_titularisation.pdf");  // ✅ Obligatoire pour un nouveau

		// Assigner la candidature à l’enseignant
		enseignant2.setCandidatures(List.of(candidature2));

		utilisateurService.ajouter_Utilisateur(enseignant2);
		utilisateurService.ajouter_UtilisateurRoles(enseignant2, roleEnseignant);




		System.out.println("Données initiales insérées avec succès !");*/
	}
}