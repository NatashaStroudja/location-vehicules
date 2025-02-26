package com.accenture.service.mapper;

import com.accenture.repository.entity.Voiture;
import com.accenture.service.dto.VoitureRequestDto;
import com.accenture.service.dto.VoitureResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoitureMapper {
    Voiture toVoiture(VoitureRequestDto voitureRequestDto);
     VoitureResponseDto toVoitureResponseDto (Voiture voiture);
}
