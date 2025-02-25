package com.uasz.gestion_voyages.Utilisateur.modele;

import com.uasz.gestion_voyages.Authentification.modele.Utilisateur;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ENSEIGNANT")
public class Enseignant extends Utilisateur {

    private String specialite;

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidature> candidatures;  // ✅ Liste des candidatures

    // ✅ Méthode pour vérifier si l'enseignant a déjà voyagé
    public boolean aDejaVoyage() {
        return candidatures != null && !candidatures.isEmpty();
    }
}
