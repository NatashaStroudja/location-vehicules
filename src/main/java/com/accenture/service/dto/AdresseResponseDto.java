package com.accenture.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AdresseResponseDto(
        int id,
        @Schema(example = "1, rue du Pont")
        String rue,
        @Schema(example = "10000")
        String codePostal,
        @Schema(example = "Prague")
        String ville) {
}
