package com.accenture.service.dto;

public record AdresseResponseDto(
        int id, String rue,
        String codePostal,
        String ville) {
}
