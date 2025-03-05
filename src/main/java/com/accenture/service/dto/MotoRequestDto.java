package com.accenture.service.dto;

import com.accenture.shared.Permis;
import com.accenture.shared.Transmission;
import com.accenture.shared.TypeMoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MotoRequestDto(
        @NotBlank(message = "champ obligatoire")
        String marque,
        @NotBlank(message = "champ obligatoire")
        String model,
        @NotBlank(message = "champ obligatoire")
        String couleur,
        @NotNull(message = "champ obligatoire")
        Integer nombreDeCylindres,
        @NotNull(message = "champ obligatoire")
        Integer cylindree,
        @NotNull(message = "champ obligatoire")
        Double poids,
        @NotNull(message = "champ obligatoire")
        Double puissance,
        @NotNull(message = "champ obligatoire")
        Double hauteurDeSelle,
        @NotNull(message = "champ obligatoire")
        Transmission transmission,

        TypeMoto typeMoto,
        Permis permis) {

}
