package com.accenture.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UtilisateurConnecte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    private String nom;

    @Basic(optional = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    private String email;

    @Basic(optional = false)
    private String password;
}
