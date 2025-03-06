package com.accenture.service.dto;

import com.accenture.repository.entity.Client;
import com.accenture.repository.entity.Vehicule;
import com.accenture.shared.EtatLocation;

import java.time.LocalDate;

public record LocationResponseDto(
        long id,
        Client client,
        Vehicule vehicule,
        LocalDate dateDeDebut,
        LocalDate dateDeFin,
        Double kilometrage,
        Double montantTotal,
        LocalDate dateDeValidation,
        EtatLocation etatLocation
) {
}
