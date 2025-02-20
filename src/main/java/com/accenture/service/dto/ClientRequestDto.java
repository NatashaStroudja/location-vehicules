package com.accenture.service.dto;

import com.accenture.model.Permis;
import com.accenture.repository.entity.Adresse;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientRequestDto(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,
        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,
        @Id
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$",
                message = "Le mot de passe doit contenir entre 8 et 16 caractères, avec au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi & # @ - _ §")
        String password,
        @NotBlank (message = "L'adresse est obligatoire!")
        Adresse adresse,
        @NotNull (message = "La date de naissance est obligatoire!")
        LocalDate dateNaissance,
        Permis categoriePermis
) {
}
