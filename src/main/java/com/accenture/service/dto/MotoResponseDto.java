package com.accenture.service.dto;

import com.accenture.shared.Permis;
import com.accenture.shared.Transmission;
import com.accenture.shared.TypeMoto;

import java.util.List;

public record MotoResponseDto(
        Integer id,
        String marque,
        String model,
        String couleur,
        Integer nombreDeCylindres,
        Integer cylindree,
        Double poids,
        Double puissance,
        Double hauteurDeSelle,
        Transmission transmission,
        TypeMoto typeMoto,
        Permis permis
) {
}
