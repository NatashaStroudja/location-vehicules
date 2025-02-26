package com.accenture.repository.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Admin extends UtilisateurConnecte {
    private String fonction;
}
