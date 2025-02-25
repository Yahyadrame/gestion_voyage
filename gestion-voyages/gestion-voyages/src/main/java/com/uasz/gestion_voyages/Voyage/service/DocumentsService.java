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

    public List<Documents> listerDocuments() {
        return documentsRepository.findAll();
    }

    public Documents ajouterDocument(Documents document) {
        return documentsRepository.save(document);
    }

    public Documents obtenirDocument(Long id) {
        return documentsRepository.findById(id).orElseThrow();
    }

    public Documents modifierDocument(Long id, Documents document) {
        document.setId(id);
        return documentsRepository.save(document);
    }

    public void supprimerDocument(Long id) {
        documentsRepository.deleteById(id);
    }
}