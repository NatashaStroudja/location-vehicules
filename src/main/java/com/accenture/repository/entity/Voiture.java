package com.accenture.repository.entity;

import com.accenture.shared.*;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Représente une voiture dans le système.
 * La classe hérite de la classe {@link Vehicule} et ajoute des informations spécifiques liées aux voitures,
 * telles que le nombre de places, le type de carburant ou d'énergie, le nombre de portes, la transmission, la climatisation, etc.
 * Cette classe est mappée à une table unique dans la base de données via l'annotation {@link InheritanceType.SINGLE_TABLE},
 * ce qui permet de stocker tous les types de véhicules, y compris les voitures, dans la même table.
 */
@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Voiture extends Vehicule{
    @Basic(optional = false)
   private Integer nombreDePlaces;
    @Enumerated(EnumType.STRING)
   private CarburantOuEnergie carburantOuEnergie;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private NombreDePortes nombreDePortes;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Basic(optional = false)
    private Boolean clim;
    @Basic(optional = false)
    private Integer nombreDeBagages;

    @Enumerated(EnumType.STRING)
    private TypeVoiture typeVoiture;

    @Enumerated(EnumType.STRING)
    private Permis permis;
}
