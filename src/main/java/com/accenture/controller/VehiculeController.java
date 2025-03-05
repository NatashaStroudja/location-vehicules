package com.accenture.controller;

import com.accenture.service.VehiculeService;
import com.accenture.service.dto.VehiculeResponseDto;
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
@RequestMapping("/vehicules")
@Tag(name = "Vehicules", description = "Géstion complète du parc")
public class VehiculeController {
    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    @Operation(summary = "Afficher la liste des vehicules")
    VehiculeResponseDto afficherTous() {
        return vehiculeService.trouverTous();
    }

     @GetMapping("/search")
     VehiculeResponseDto afficherParDate(
            @RequestParam(required = false) LocalDateTime dateDebut,
            @RequestParam(required = false )  LocalDateTime dateFin
            ) {
         return  vehiculeService.rechercherParDate(dateDebut, dateFin);
     }
}
