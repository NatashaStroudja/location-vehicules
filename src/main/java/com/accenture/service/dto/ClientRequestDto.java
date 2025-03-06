package com.accenture.service.dto;

import com.accenture.shared.Permis;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record ClientRequestDto(

        @NotBlank(message = "Le nom est obligatoire")
        @Schema(example = "Kafka")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        @Schema(example = "Franz")
        String prenom,

        @NotNull
        @Email(message = "l'adresse e-mail doit être valide")
        @Schema(example = "client@example.com")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$",
                message = "Le mot de passe doit contenir entre 8 et 16 caractères, avec au moins une majuscule, une minuscule, un chiffre, et un caractère spécial parmi & # @ - _ §")
        @Schema(example = "JaiPeur1&")
        String password,

        @NotNull (message = "L'adresse est obligatoire!")
        AdresseRequestDto adresse,

        @NotNull
        @Past(message = "La date de naissance est obligatoire!")
        @Schema(example = "1883-07-03")
        LocalDate dateNaissance,

        List<Permis> permis
) {
}
