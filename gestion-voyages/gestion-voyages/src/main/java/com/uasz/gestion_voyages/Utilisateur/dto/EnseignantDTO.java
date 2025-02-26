package com.uasz.gestion_voyages.Utilisateur.dto;

import com.uasz.gestion_voyages.Authentification.dto.UtilisateurDTO;
import com.uasz.gestion_voyages.Voyage.modele.Candidature;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data

public class EnseignantDTO extends UtilisateurDTO {
    private String specialite;
    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidature> candidatures;  // ✅ Liste des candidatures

    // ✅ Méthode pour vérifier si l'enseignant a déjà voyagé
    public boolean aDejaVoyage() {
        return candidatures != null && !candidatures.isEmpty();
    }
}
