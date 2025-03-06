package com.accenture.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AdminRequestDto(
        @NotBlank(message = "Le nom est obligatoire")
        @Schema(example = "Turturro")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        @Schema(example = "John")
        String prenom,

        @NotNull
        @Email(message = "l'adresse e-mail doit être valide")
        @Schema(example = "john@tut.by")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$",
                message = "Le mot de passe doit contenir entre 8 et 16 caractères, avec au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi & # @ - _ §")
        @Schema(example = "JeGere1&4")
        String password,

        @Schema(example = "admin")
        String fonction

) {
}
