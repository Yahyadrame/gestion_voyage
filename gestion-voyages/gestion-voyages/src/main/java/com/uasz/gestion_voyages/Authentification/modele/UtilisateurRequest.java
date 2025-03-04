package com.uasz.gestion_voyages.Authentification.modele;

import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurFinancier;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRH;
import com.uasz.gestion_voyages.Utilisateur.modele.DirecteurRecherche;
import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRequest {
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String motDePasse;
    private String telephone;
    private String role;

    // Getters et Setters



    public DirecteurRecherche toDirecteurRecherche() {
        DirecteurRecherche directeurRecherche = new DirecteurRecherche();
        directeurRecherche.setNom(this.nom);
        directeurRecherche.setPrenom(this.prenom);
        directeurRecherche.setEmail(this.email);
        directeurRecherche.setUsername(this.username);
        directeurRecherche.setMotDePasse(this.motDePasse);
        directeurRecherche.setTelephone(this.telephone);
        return directeurRecherche;
    }

    public Enseignant toEnseignant() {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(this.nom);
        enseignant.setPrenom(this.prenom);
        enseignant.setEmail(this.email);
        enseignant.setUsername(this.username);
        enseignant.setMotDePasse(this.motDePasse);
        enseignant.setTelephone(this.telephone);
        return enseignant;
    }

    public DirecteurRH toDirecteurRH() {
        DirecteurRH directeurRH = new DirecteurRH();
        directeurRH.setNom(this.nom);
        directeurRH.setPrenom(this.prenom);
        directeurRH.setEmail(this.email);
        directeurRH.setUsername(this.username);
        directeurRH.setMotDePasse(this.motDePasse);
        directeurRH.setTelephone(this.telephone);
        return directeurRH;
    }

    public DirecteurFinancier toDirecteurFinancier() {
        DirecteurFinancier directeurFinancier = new DirecteurFinancier();
        directeurFinancier.setNom(this.nom);
        directeurFinancier.setPrenom(this.prenom);
        directeurFinancier.setEmail(this.email);
        directeurFinancier.setUsername(this.username);
        directeurFinancier.setMotDePasse(this.motDePasse);
        directeurFinancier.setTelephone(this.telephone);
        return directeurFinancier;
    }
}
