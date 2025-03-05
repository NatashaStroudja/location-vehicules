package com.accenture.service.dto;

import com.accenture.shared.Permis;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record ClientRequestDto(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        @NotNull
        @Email(message = "l'adresse e-mail doit être valide")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$",
                message = "Le mot de passe doit contenir entre 8 et 16 caractères, avec au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi & # @ - _ §")
        String password,

        @NotNull (message = "L'adresse est obligatoire!")
        AdresseRequestDto adresse,

        @NotNull
        @Past(message = "La date de naissance est obligatoire!")
        LocalDate dateNaissance,

        List<Permis> permis
) {
}
