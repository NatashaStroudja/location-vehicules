package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.VoitureRequestDto;
import com.accenture.service.dto.VoitureResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface VoitureService {

    VoitureResponseDto ajouterVoiture(VoitureRequestDto voitureRequestDto) throws VehiculeException;

    List<VoitureResponseDto> trouverToutesVoitures();

    VoitureResponseDto trouver(int id) throws EntityNotFoundException;

    void supprimer(int id) throws EntityNotFoundException;

    VoitureResponseDto modifierPartiellement(int id, VoitureRequestDto voitureRequestDto) throws VehiculeException;
}
