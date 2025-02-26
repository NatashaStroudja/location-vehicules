package com.accenture.repository.entity;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
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
    private List<Permis> categoriePermis;

    @Basic(optional = false)
    private Boolean desactive = false ;
}
