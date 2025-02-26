package com.accenture.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AdminRequestDto(
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


        String fonction

) {
}
