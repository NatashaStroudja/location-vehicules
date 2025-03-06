package com.accenture.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AdresseRequestDto(
        @NotBlank(message = "La rue est obligatoire")
        @Schema(example = "1, Rue du Pont")
        String rue,

        @Schema(example = "10000")
        @NotBlank(message = "Le code postal est obligatoire")
        String codePostal,

        @Schema(example = "Prague")
        @NotBlank(message = "La ville est obligatoire")
        String ville
) {
}
