package com.accenture.controller;

import com.accenture.service.VoitureService;
import com.accenture.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
/**
 * Contrôleur pour la gestion des voitures.
 * Ce contrôleur permet de l'ajout, la suppression, la modification, et la récupération des voitures.
 */
@RestController
@RequestMapping("/voitures")
@Tag(name = "Voitures", description = "Gestion des voitures")
public class VoitureController {
    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }
    /**
     * Cette méthode permet d'ajouter une nouvelle voiture  à la base de données.
     *
     * @param voitureRequestDto les informations de la voiture à ajouter
     * @return une réponse HTTP avec un statut 201 (Created) si la voiture est ajoutée avec succès
     */
    @PostMapping
    @Operation(summary = "Ajouter une nouvelle voiture", description = "Ajouter une nouvelle voiture à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voiture est créée avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody VoitureRequestDto voitureRequestDto) {
        VoitureResponseDto voitureEnreg = voitureService.ajouterVoiture(voitureRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(voitureEnreg.id())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * Cette méthode permet d'afficher la liste de toutes les voitures enregistrées.
     *
     * @return une liste de DTO représentant les voitures
     */
    @GetMapping
    @Operation(summary = "Afficher la liste des voitures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des voitures récupérée avec succès")
    })
    List<VoitureResponseDto> listerDesVoitures() {
        return voitureService.trouverToutesVoitures();
    }
    /**
     * Cette méthode permet de récupérer une voiture en utilisant son id.
     *
     * @param id id de la voiture
     * @return la voiture trouvée
     */
    @GetMapping("/{id}")
    @Operation(summary = "Afficher une voiture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voiture trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Voiture non trouvée par l'id fourni"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<VoitureResponseDto> afficherVoiturePourAdmin(@PathVariable("id") int id) {
        VoitureResponseDto voitureTrouve = voitureService.trouver(id);
        return ResponseEntity.ok(voitureTrouve);
    }
    /**
     * Cette méthode permet de supprimer une voiture de la base de données par son ID.
     *
     * @param id l'identifiant de la voiture à supprimer
     * @return une réponse HTTP avec un statut 204 (No Content) si la suppression a réussi
     */
    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer une voiture par id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "L'id n'est pas trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, l'ID est mal formaté ou manquant"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors de la suppression")
    })
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        voitureService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /**
     * Cette méthode permet de modifier certains attributs d'une voiture existante.
     *
     * @param id l'identifiant de la voiture à modifier
     * @param voitureRequestDto les informations à mettre à jour pour la voiture
     * @return voiture mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier une voiture partiellement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voiture modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètre manquant ou mal formaté "),
            @ApiResponse(responseCode = "404", description = "Voiture non trouvée pour l'id spécifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<VoitureResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody VoitureRequestDto voitureRequestDto){
        VoitureResponseDto response = voitureService.modifierPartiellement(id, voitureRequestDto);
        return ResponseEntity.ok(response);
    }
}
