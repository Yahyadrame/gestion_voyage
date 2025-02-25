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
public class VoyageEtude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lieu;
    private Date dateDepart;
    private Date dateRetour;
    private String objectif;

    @OneToMany(mappedBy = "voyage", cascade = CascadeType.ALL)
    private List<Candidature> candidatures;
}