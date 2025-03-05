package com.accenture.service;

import com.accenture.service.dto.VehiculeResponseDto;
import com.accenture.shared.CategorieVehicules;

import java.time.LocalDateTime;
import java.util.List;

public interface VehiculeService {
    VehiculeResponseDto trouverTous();

    VehiculeResponseDto rechercherParDate(LocalDateTime dateDebut, LocalDateTime dateFin);
}
