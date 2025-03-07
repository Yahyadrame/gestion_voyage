package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.Documents;
import com.uasz.gestion_voyages.Voyage.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentsService {

    @Autowired
    private DocumentsRepository documentsRepository;

    // Ajouter un document
    public Documents ajouterDocument(Documents document) {
        return documentsRepository.save(document);
    }

    // Lister les documents par candidature
    public List<Documents> listerDocumentsParCandidature(Long candidatureId) {
        return documentsRepository.findByCandidatureId(candidatureId);
    }

    // Supprimer un document
    public void supprimerDocument(Long id) {
        documentsRepository.deleteById(id);
    }
}