package com.accenture.repository.entity;

import com.accenture.model.Permis;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class Client extends UtilisateurConnecte{
    @OneToOne(cascade = CascadeType.ALL)
    @Basic(optional = false)
    private Adresse adresse;
    @Basic(optional = false)
    private LocalDate dateDeNaissance;
    @Basic(optional = false)
    private LocalDate dateDInscription;
    @Enumerated(EnumType.STRING)
    private Permis categoriePermis;
    @Basic(optional = false)
    private Boolean desactive;
}
