package com.accenture.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe abstraite représentant un véhicule dans le système.
 * Cette classe est la classe mére pour d'autres types de véhicules, comme {@link Voiture}, {@link Moto}, etc.
 * Elle contient des informations communes à tous les véhicules, telles que la marque, le modèle, la couleur,
 * le tarif de location journalier, le kilométrage, et le statut actif ou retiré du parc.
 * Cette classe est mappée à une table unique dans la base de données,
 * ce qui permet de stocker tous les types de véhicules dans la même table.
 */
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    private String marque;
    @Basic(optional = false)
    private String model;
    @Basic(optional = false)
    private String couleur;

    private Double tarifDeLocationJour;
    private Double kilometrage;
    private Boolean actif;
    private Boolean retireDuPark;
}
