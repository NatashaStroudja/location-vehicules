package com.accenture.repository.entity;

import com.accenture.shared.Permis;
import com.accenture.shared.Transmission;
import com.accenture.shared.TypeMoto;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Moto extends Vehicule {
    @Basic(optional = false)
    private Integer nombreDeCylindres;
    @Basic(optional = false)
    private Integer cylindree;
    @Basic(optional = false)
    private Double poids;
    @Basic(optional = false)
    private Double puissance;
    @Basic(optional = false)
    private Double hauteurDeSelle;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private TypeMoto typeMoto;
    @Enumerated(EnumType.STRING)
    private Permis permis;
}
