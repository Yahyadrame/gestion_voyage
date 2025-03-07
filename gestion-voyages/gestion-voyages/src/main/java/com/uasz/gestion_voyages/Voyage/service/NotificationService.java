package com.uasz.gestion_voyages.Voyage.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void envoyerNotification(String email, String message) {
        // Implémenter l'envoi de notification par email ou autre moyen
        System.out.println("Notification envoyée à " + email + ": " + message);
    }
}