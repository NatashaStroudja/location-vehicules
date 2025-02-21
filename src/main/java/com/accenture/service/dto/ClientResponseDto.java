package com.accenture.service.dto;

import com.accenture.shared.Permis;

import java.time.LocalDate;

public record ClientResponseDto(
        int id,
        String nom,
        String prenom,
        String email,
        AdresseResponseDto adresseResponseDto,
        LocalDate dateDeNaissance,
        LocalDate dateDInscription,
        Permis categoriePermis,
        Boolean desactive
) {
}
