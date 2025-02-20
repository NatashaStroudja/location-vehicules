package com.accenture.repository.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Administrateur extends UtilisateurConnecte {
    private String fonction;
}
