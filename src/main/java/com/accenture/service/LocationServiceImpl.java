package com.accenture.service;

import com.accenture.repository.LocationDao;
import com.accenture.service.dto.LocationResponseDto;
import com.accenture.service.mapper.LocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationDao locationDao;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationDao locationDao, LocationMapper locationMapper) {
        this.locationDao = locationDao;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<LocationResponseDto> trouverToutes() {
        return locationDao.findAll().stream()
                .map(locationMapper::toLocationResponseDto)
                .toList();
    }
}

