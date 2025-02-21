package com.accenture.service.mapper;

import com.accenture.repository.entity.Adresse;
import com.accenture.service.dto.AdresseRequestDto;
import com.accenture.service.dto.AdresseResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresseMapper {
    Adresse toAdresse (AdresseRequestDto adresseRequestDto);
    AdresseResponseDto toAdresseResponseDto (Adresse adresse);
}
