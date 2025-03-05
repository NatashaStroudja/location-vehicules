package com.accenture.service.dto;

import com.accenture.repository.entity.Client;
import com.accenture.repository.entity.Vehicule;
import com.accenture.shared.EtatLocation;

import java.time.LocalDateTime;

public record LocationRequestDto(
        Client client,
        Vehicule vehicule,
        LocalDateTime dateDeDebut,
        LocalDateTime dateDeFin,
        Double kilometrage,
        Double montantTotal,
        LocalDateTime dateDeValidation,
        EtatLocation etatLocation
) {
}
