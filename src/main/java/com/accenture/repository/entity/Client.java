package com.accenture.repository.entity;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.List;
/**
 * Représente un client dans le système.
 * Le client est une spécialisation de {@link UtilisateurConnecte}, avec des informations supplémentaires telles que l'adresse,
 * la date de naissance, la date d'inscription et la liste des permis associés.
 * Cette entité est mappée à une table dans la base de données et permet de persister les informations liées aux clients.
 */
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
    private List<Permis> permis;


    private Boolean desactive = false ;
}
