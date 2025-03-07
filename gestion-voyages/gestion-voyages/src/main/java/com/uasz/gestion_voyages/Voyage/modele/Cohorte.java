package com.uasz.gestion_voyages.Voyage.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cohorte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Nom de la cohorte (ex: "Cohorte 2024")
    private Date dateOuverture; // Date d'ouverture des candidatures
    private Date dateFermeture; // Date de fermeture des candidatures
    private boolean estOuverte; // Indique si la cohorte est ouverte ou non
    private String statut;

    @OneToMany(mappedBy = "cohorte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidature> candidatures; // Liste des candidatures associées à cette cohorte
}