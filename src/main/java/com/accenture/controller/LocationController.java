package com.accenture.controller;


import com.accenture.repository.entity.Location;
import com.accenture.service.LocationService;
import com.accenture.service.dto.LocationResponseDto;
import com.accenture.shared.CategorieVehicules;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/locations")
@Tag(name = "Locations", description = "Gestion des locations")
public class LocationController {
private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
@GetMapping
@Operation(summary = "Afficher la liste des locations")
  List<LocationResponseDto> afficherToutesLocations (){
        return locationService.trouverToutes();
}
}

