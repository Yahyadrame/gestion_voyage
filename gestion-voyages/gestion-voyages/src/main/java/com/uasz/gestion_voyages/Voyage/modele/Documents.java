package com.uasz.gestion_voyages.Voyage.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type; // PDF, JPEG, etc.
    private String cheminFichier;

    @ManyToOne
    @JoinColumn(name = "candidature_id")
    private Candidature candidature;
}