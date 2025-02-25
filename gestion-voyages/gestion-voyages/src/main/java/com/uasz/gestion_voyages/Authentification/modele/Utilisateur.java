package com.uasz.gestion_voyages.Authentification.modele;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_personnel")
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gestion automatique de l'auto-incrémentation
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 200)
    private String motDePasse;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;

    @Column(length = 15)
    private String telephone;
}
