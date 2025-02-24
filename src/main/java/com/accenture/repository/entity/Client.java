package com.accenture.repository.entity;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class Client extends UtilisateurConnecte{
    @OneToOne(cascade = CascadeType.ALL)
    @Basic(optional = false)
    private Adresse adresse;

    @Basic(optional = false)
    @Column(name = "date_de_naissance")
    private LocalDate dateNaissance;

    @CreationTimestamp
    @Basic(optional = false)
    private LocalDate dateDInscription;

    @Enumerated(EnumType.STRING)
    private Permis categoriePermis;

    private Boolean desactive = false ;
}
