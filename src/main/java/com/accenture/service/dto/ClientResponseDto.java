package com.accenture.service.dto;

import com.accenture.shared.Permis;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record ClientResponseDto(
        int id,
        @Schema(example = "Kafka")
        String nom,
        @Schema(example = "Franz")
        String prenom,
        @Schema(example = "client@example.com")
        String email,
        AdresseResponseDto adresseResponseDto,
        @Schema(example = "1883-07-03")
        LocalDate dateDeNaissance,
        @Schema(example = "1900-07-03")
        LocalDate dateDInscription,
        List<Permis> permis
) {
}
