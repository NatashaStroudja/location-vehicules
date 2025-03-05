package com.accenture.service.dto;

import java.util.List;

public record VehiculeResponseDto(List<VoitureResponseDto> voitures,
                                  List<MotoResponseDto> motos) {
}
