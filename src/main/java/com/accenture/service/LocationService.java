package com.accenture.service;


import com.accenture.service.dto.LocationResponseDto;
import java.util.List;

public interface LocationService {
    List<LocationResponseDto> trouverToutes();
}
