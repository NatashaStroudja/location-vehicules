package com.accenture.controller;

import com.accenture.service.VehiculeService;
import com.accenture.service.dto.VehiculeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


/**
 * Contrôleur pour la gestion du parc des vehicules.
 * Ce contrôleur permet de l'affichage des tous les vehilules et le recherche par date du début et de la fin de la location.
 */

@RestController
@RequestMapping("/vehicules")
@Tag(name = "Vehicules", description = "Géstion complète du parc")
public class VehiculeController {
    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }
    /**
     * Méthode pour récupérer tous les véhicules disponibles.
     * Cette méthode appelle le service pour récupérer la liste de tous les véhicules
     * enregistrés dans le système.
     *
     * @return  Liste des véhicules disponibles sous forme de réponse DTO.
     */
    @GetMapping
    @Operation(summary = "Afficher la liste des vehicules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des vehicules récupérée avec succès")
    })
    VehiculeResponseDto afficherTous() {
        return vehiculeService.trouverTous();
    }
    /**
     * Méthode pour récupérer les véhicules en fonction de la période donnée (date de début et date de fin).
     *
     * @param dateDebut La date de début de la période de recherche.
     * @param dateFin La date de fin de la période de recherche.
     * @return {@link VehiculeResponseDto} Liste des véhicules disponibles pendant la période donnée.
     */
     @GetMapping("/search")
     @Operation(summary = "Rechercher les véhicules par date du début et de la fin de la location")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Les véhicules ont été récupérés avec succès."),
             @ApiResponse(responseCode = "400", description = "Les paramètres de date sont invalides."),
             @ApiResponse(responseCode = "500", description = "Erreur interne du serveur.")
     })
     VehiculeResponseDto afficherParDate(
            @RequestParam
            @Schema(description = "Date de début de la période de recherche. Format attendu : yyyy-MM-dd", example = "2024-03-23")
            LocalDate dateDebut,
            @RequestParam
            @Schema(description = "Date de la fin de la période de recherche. Format attendu : yyyy-MM-dd", example = "2024-03-23")
            LocalDate dateFin
            ) {
         return  vehiculeService.rechercherParDate(dateDebut, dateFin);
     }
}
