package com.accenture.service.dto;

import com.accenture.shared.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record VoitureResponseDto(
        Integer id,
        @Schema(example = "Subaru")
        String marque,
        @Schema(example = "Impreza")
        String model,
        @Schema(example = "Rouge")
        String couleur,
        @Schema(example = "5")
        Integer nombreDePlaces,
        CarburantOuEnergie carburantOuEnergie,
        NombreDePortes nombreDePortes,
        Transmission transmission,
        @Schema(example = "Oui")
        Boolean clim,
        @Schema(example = "4")
        Integer nombreDeBagages,
        TypeVoiture typeVoiture,
        Permis permis) {
}
