package com.accenture.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
