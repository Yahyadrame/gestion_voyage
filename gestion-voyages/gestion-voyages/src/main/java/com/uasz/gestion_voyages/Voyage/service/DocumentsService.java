package com.uasz.gestion_voyages.Voyage.service;

import com.uasz.gestion_voyages.Voyage.modele.Documents;
import com.uasz.gestion_voyages.Voyage.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentsService {
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private DocumentsRepository documentsRepository;

    public List<Documents> listerDocuments() {
        return documentsRepository.findAll();
    }
    // Enregistrer un document
    public Documents enregistrerDocument(MultipartFile fichier) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + fichier.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(fichier.getInputStream(), filePath);

            Documents document = new Documents();
            document.setNom(fileName);
            document.setCheminFichier(filePath.toString());

            return documentsRepository.save(document);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier.", e);
        }
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
