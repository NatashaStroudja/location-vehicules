package com.accenture.repository.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Classe fille (représentant Admin) de la classe {@link UtilisateurConnecte}.Elle possède des informations supplémentaires,
 * telles que la fonction.
 * Cette entité est mappée à une table dans la base de données, permettant de persister les informations relatives aux administrateurs.
 */

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Admin extends UtilisateurConnecte {
    private String fonction;
}
