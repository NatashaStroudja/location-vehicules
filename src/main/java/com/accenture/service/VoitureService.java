package com.accenture.service;

import com.accenture.exception.VoitureException;
import com.accenture.service.dto.VoitureRequestDto;
import com.accenture.service.dto.VoitureResponseDto;

public interface VoitureService {

    VoitureResponseDto ajouterVoiture(VoitureRequestDto voitureRequestDto) throws VoitureException;
}
