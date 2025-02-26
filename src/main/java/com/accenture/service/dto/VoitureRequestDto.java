package com.accenture.service.dto;

import com.accenture.shared.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VoitureRequestDto(
        @NotBlank(message = "champ obligatoire")
        String marque,
        @NotBlank(message = "champ obligatoire")
        String model,
        @NotBlank(message = "champ obligatoire")
        String couleur,
        @NotNull(message = "champ obligatoire")
        Integer nombreDePlaces,

        CarburantOuEnergie carburantOuEnergie,
        @NotNull(message = "champ obligatoire")
        NombreDePortes nombreDePortes,
        @NotNull(message = "champ obligatoire")
        Transmission transmission,
        @NotNull(message = "champ obligatoire")
        Boolean clim,
        @NotNull(message = "champ obligatoire")
        Integer nombreDeBagages,

        TypeVoiture typeVoiture,
        Permis permis

) {
}
