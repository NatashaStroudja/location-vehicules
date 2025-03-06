package com.accenture.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Représente une adresse.
 * Cette entité est mappée à une table dans la base de données pour stocker les informations liées à une adresse.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic(optional = false)
    private String rue;
    @Basic(optional = false)
    private String codePostal;
    @Basic(optional = false)
    private String ville;
}
