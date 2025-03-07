package com.accenture.service.dto;

import com.accenture.shared.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VoitureRequestDto(
        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Subaru")
        String marque,
        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Impreza")
        String model,
        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Rouge")
        String couleur,
        @NotNull(message = "champ obligatoire")
        @Schema(example = "5")
        Integer nombreDePlaces,

        CarburantOuEnergie carburantOuEnergie,
        @NotNull(message = "champ obligatoire")
        @Schema(example = "TROIS")
        NombreDePortes nombreDePortes,
        @NotNull(message = "champ obligatoire")
        @Schema(example = "AUTO")
        Transmission transmission,
        @NotNull(message = "champ obligatoire")
        @Schema(example = "true")
        Boolean clim,
        @NotNull(message = "champ obligatoire")
        @Schema(example = "4")
        Integer nombreDeBagages,

        TypeVoiture typeVoiture,
        Permis permis
) {
}
