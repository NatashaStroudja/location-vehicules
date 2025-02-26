package com.accenture.service.dto;

import com.accenture.shared.*;

import java.util.List;

public record VoitureResponseDto(
        Integer id,
        String marque,
        String model,
        String couleur,
        Integer nombreDePlaces,
        CarburantOuEnergie carburantOuEnergie,
        NombreDePortes nombreDePortes,
        Transmission transmission,
        Boolean clim,
        Integer nombreDeBagages,
        TypeVoiture typeVoiture,
        Permis permis) {
}
