package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.MotoRequestDto;
import com.accenture.service.dto.MotoResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface MotoService {

    MotoResponseDto ajouter(MotoRequestDto motoRequestDto) throws VehiculeException;

    List<MotoResponseDto> trouverToutes();

    MotoResponseDto trouver(int id) throws EntityNotFoundException;

    void supprimer(int id) throws EntityNotFoundException;

    MotoResponseDto modifierPartiellement(int id, MotoRequestDto motoRequestDto) throws VehiculeException;
}