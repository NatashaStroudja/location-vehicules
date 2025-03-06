package com.accenture.controller;

import com.accenture.service.LocationService;
import com.accenture.service.dto.LocationResponseDto;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * Contrôleur pour gérer les locations.
 * Cette classe expose une API permettant de récupérer toutes les locations.
 */
@RestController
@RequestMapping("/locations")
@Tag(name = "Locations", description = "Gestion des locations")
@Slf4j
public class LocationController {
private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    /**
     * Récupère la liste de toutes les locations.
     * Cette méthode expose une API GET pour obtenir toutes les locations disponibles.
     *
     * @return une liste de {@link LocationResponseDto} contenant les informations de toutes les locations
     */
@GetMapping
@Operation(summary = "Afficher la liste des locations")
@ApiResponse(responseCode = "200", description = "Liste des locations récupérée avec succès")

  List<LocationResponseDto> afficherToutesLocations (){
    log.info("Locations affichées avec succès");
        return locationService.trouverToutes();
}
}

