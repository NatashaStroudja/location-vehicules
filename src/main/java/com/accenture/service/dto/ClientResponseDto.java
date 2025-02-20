package com.accenture.service.dto;

import com.accenture.model.Permis;
import com.accenture.repository.entity.Adresse;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientResponseDto(
        int id,
        String nom,
        String prenom,
        String email,
        Adresse adresse,
        LocalDate dateDeNaissance,
        LocalDate dateDInscription,
        Permis categoriePermis,
        Boolean desactive
) {
}
