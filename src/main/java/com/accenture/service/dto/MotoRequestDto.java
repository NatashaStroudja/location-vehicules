package com.accenture.service.dto;

import com.accenture.shared.Permis;
import com.accenture.shared.Transmission;
import com.accenture.shared.TypeMoto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record MotoRequestDto(
        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Harley-Davidson")
        String marque,

        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Street Bob")
        String model,

        @NotBlank(message = "champ obligatoire")
        @Schema(example = "Rouge")
        String couleur,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "3")
        Integer nombreDeCylindres,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "4")
        Integer cylindree,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "50.00")
        Double poids,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "35.00")
        Double puissance,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "56.60")
        Double hauteurDeSelle,

        @NotNull(message = "champ obligatoire")
        @Schema(example = "AUTO")
        Transmission transmission,

        TypeMoto typeMoto,
        Permis permis) {

}
