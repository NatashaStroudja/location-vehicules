package com.accenture.service.mapper;

import com.accenture.repository.entity.Location;
import com.accenture.service.dto.LocationRequestDto;
import com.accenture.service.dto.LocationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface LocationMapper {
   Location toLocation(LocationRequestDto motoRequestDto);
   LocationResponseDto toLocationResponseDto (Location location);
}
