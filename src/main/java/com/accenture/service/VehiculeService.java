package com.accenture.service;

import com.accenture.service.dto.VehiculeResponseDto;


import java.time.LocalDate;


public interface VehiculeService {
    VehiculeResponseDto trouverTous();

    VehiculeResponseDto rechercherParDate(LocalDate dateDebut, LocalDate dateFin);
}
