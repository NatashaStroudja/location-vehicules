package com.accenture.service.dto;

import com.accenture.shared.Permis;
import com.accenture.shared.Transmission;
import com.accenture.shared.TypeMoto;
import io.swagger.v3.oas.annotations.media.Schema;


public record MotoResponseDto(
        Integer id,
        @Schema(example = "Harley-Davidson")
        String marque,
        @Schema(example = "Street Bob")
        String model,
        @Schema(example = "Rouge")
        String couleur,
        @Schema(example = "3")
        Integer nombreDeCylindres,
        @Schema(example = "4")
        Integer cylindree,
        @Schema(example = "40")
        Double poids,
        @Schema(example = "400")
        Double puissance,
        @Schema(example = "45")
        Double hauteurDeSelle,
        Transmission transmission,
        TypeMoto typeMoto,
        Permis permis
) {
}
