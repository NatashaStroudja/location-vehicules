package com.accenture.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe abstraite représentant un utilisateur connecté dans le système.
 * Cette classe est la classe mére pour d'autres entités utilisateurs, comme {@link Admin} et {@link Client}.
 * Elle contient des informations communes à tous les types d'utilisateurs connectés, telles que l'identifiant,
 * le nom, le prénom, l'email et le mot de passe.
 * Cette classe est mappée à une table unique dans la base de données,
 * ce qui permet de stocker tous les utilisateurs (de type Admin, Client, etc.) dans la même table.
 */
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
