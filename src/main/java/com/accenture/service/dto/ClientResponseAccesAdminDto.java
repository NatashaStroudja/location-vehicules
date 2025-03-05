package com.accenture.service.dto;

import com.accenture.shared.Permis;

import java.time.LocalDate;
import java.util.List;
// pour que admin puisse voir les infos de clients nottement active ou pas active, mais il ne peut pas modifier
public record ClientResponseAccesAdminDto(
        int id,
        String nom,
        String prenom,
        String email,
        AdresseResponseDto adresseResponseDto,
        LocalDate dateDeNaissance,
        LocalDate dateDInscription,
        List<Permis> permis,
        Boolean desactive
) {

}
