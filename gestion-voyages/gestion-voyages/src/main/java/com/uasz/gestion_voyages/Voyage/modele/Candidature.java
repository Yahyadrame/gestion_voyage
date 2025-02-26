package com.uasz.gestion_voyages.Voyage.modele;

import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statut; // EN_ATTENTE, VALIDEE, REFUSEE
    private Date dateSoumission;

    // Informations sur le voyage
    private String lieu;
    private String periode; // Utilisé dans le contrôleur
    private Date dateDepart;
    private Date dateRetour;

    // Conditions pour les nouveaux enseignants
    private boolean titulaire; // true si l'enseignant est titulaire
    private String arreteTitularisation; // Référence de l'arrêté de titularisation

    // Conditions pour les anciens enseignants
    private String carteEmbarquement; // Justificatif du précédent voyage
    private String destinationPrecedente; // Destination du précédent voyage
    private Date dateDepartPrecedent; // Date de départ du précédent voyage
    private Date dateRetourPrecedent; // Date de retour du précédent voyage
    private String rapportVoyagePrecedent; // Rapport du dernier voyage d'étude

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private VoyageEtude voyage;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @OneToMany(mappedBy = "candidature", cascade = CascadeType.ALL)
    private List<Documents> documents;
}