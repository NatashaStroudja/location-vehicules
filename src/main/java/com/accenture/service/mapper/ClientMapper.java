package com.accenture.service.mapper;

import com.accenture.repository.entity.Client;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import org.mapstruct.Mapper;

@Mapper (componentModel =  "spring")
public interface ClientMapper {
    Client toClient (ClientRequestDto clientRequestDto);
    ClientResponseDto toClientResponseDto (Client client);
}
