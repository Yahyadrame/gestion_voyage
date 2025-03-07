package com.uasz.gestion_voyages.Voyage.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Nom du document (ex: "Carte d'embarquement")
    private String cheminFichier; // Chemin du fichier stocké

    @ManyToOne
    @JoinColumn(name = "candidature_id")
    private Candidature candidature; // Candidature à laquelle le document est associé
}