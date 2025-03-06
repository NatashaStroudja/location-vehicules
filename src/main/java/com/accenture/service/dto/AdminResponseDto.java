package com.accenture.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AdminResponseDto(int id,
                               @Schema(example = "Turturro")
                               String nom,
                               @Schema(example = "John")
                               String prenom,
                               @Schema(example = "john@tut.by")
                               String email,
                               @Schema(example = "admin")
                               String fonction
                               ) {

}
