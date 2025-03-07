package com.uasz.gestion_voyages.Voyage.modele;

import com.uasz.gestion_voyages.Utilisateur.modele.Enseignant;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lieu; // Lieu du voyage d'études
    private Date dateDepart; // Date de départ
    private Date dateRetour; // Date de retour
    private String statut; // Statut de la candidature (ex: "EN_ATTENTE", "VALIDE", "REJETE")

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant; // Enseignant qui soumet la candidature

    @ManyToOne
    @JoinColumn(name = "cohorte_id")
    private Cohorte cohorte; // Cohorte à laquelle la candidature est associée

    @OneToMany(mappedBy = "candidature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Documents> documents; // Documents justificatifs associés à la candidature

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private VoyageEtude voyage;

}