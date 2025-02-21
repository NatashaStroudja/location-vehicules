package com.accenture.service.dto;

import jakarta.validation.constraints.NotBlank;

public record AdresseRequestDto(
        @NotBlank(message = "La rue est obligatoire")
        String rue,
        @NotBlank(message = "Le code postal est obligatoire")
        String codePostal,
        @NotBlank(message = "La ville est obligatoire")
        String ville
        ) {
}
