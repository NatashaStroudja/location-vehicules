package com.accenture.service.mapper;


import com.accenture.repository.entity.Moto;
import com.accenture.service.dto.MotoRequestDto;
import com.accenture.service.dto.MotoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MotoMapper {
    Moto toMoto(MotoRequestDto motoRequestDto);
    MotoResponseDto toMotoResponseDto (Moto moto);
}
