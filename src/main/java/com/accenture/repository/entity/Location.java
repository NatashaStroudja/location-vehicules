package com.accenture.repository.entity;

import com.accenture.shared.EtatLocation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Vehicule vehicule;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double kilometrage;
    private Double montantTotal;
    private LocalDate dateDeValidation;
    @Enumerated(EnumType.STRING)
    private EtatLocation etatLocation;
}
