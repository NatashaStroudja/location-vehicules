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

    @Basic(optional = false)
    private Double tarifDeLocationJour;

    @Basic(optional = false)
    private Boolean desactive = false ;

    @Basic(optional = false)
    private Double kilometrage;

    @Basic(optional = false)
    private Boolean actif;

    @Basic(optional = false)
    private Boolean retireDuPark;






}
