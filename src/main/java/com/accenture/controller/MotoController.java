package com.accenture.controller;


import com.accenture.service.MotoService;
import com.accenture.service.dto.MotoRequestDto;
import com.accenture.service.dto.MotoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
/**
 * Contrôleur pour la gestion des motos.
 * Ce contrôleur permet de l'ajout, la suppression, la modification, et la récupération des motos.
 */
@RestController
@RequestMapping("/motos")
@Tag(name = "Motos", description = "Gestion des motos")
public class MotoController {
    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }
    /**
     * Cette méthode permet d'ajouter une nouvelle moto  à la base de données.
     *
     * @param motoRequestDto les informations de la moto à ajouter
     * @return une réponse HTTP avec un statut 201 (Created) si la moto est ajoutée avec succès
     */
    @PostMapping
    @Operation(summary = "Ajouter une nouvelle moto", description = "Ajouter une nouvelle moto à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Moto est créée avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody MotoRequestDto motoRequestDto) {
        MotoResponseDto motoEnreg = motoService.ajouter(motoRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(motoEnreg.id())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * Cette méthode permet d'afficher la liste de toutes les motos enregistrées.
     *
     * @return une liste de DTO représentant les motos
     */
    @GetMapping
    @Operation(summary = "Afficher la liste des motos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des motos récupérée avec succès")
    })
    List<MotoResponseDto> lister() {
        return motoService.trouverToutes();
    }
    /**
     * Cette méthode permet de récupérer une moto en utilisant son id.
     *
     * @param id id de la moto
     * @return la moto trouvée
     */
    @GetMapping("/{id}")
    @Operation(summary = "Afficher une moto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moto trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Moto non trouvée par l'id fourni"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<MotoResponseDto> afficher(@PathVariable("id") int id) {
        MotoResponseDto motoTrouve = motoService.trouver(id);
        return ResponseEntity.ok(motoTrouve);
    }
    /**
     * Cette méthode permet de supprimer une moto de la base de données par son ID.
     *
     * @param id l'identifiant de la moto à supprimer
     * @return une réponse HTTP avec un statut 204 (No Content) si la suppression a réussi
     */
    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer une moto par id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "L'id n'est pas trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, l'ID est mal formaté ou manquant"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors de la suppression")
    })
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        motoService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /**
     * Cette méthode permet de modifier certains attributs d'une moto existante.
     *
     * @param id l'identifiant de la moto à modifier
     * @param motoRequestDto les informations à mettre à jour pour la moto
     * @return moto mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier une moto partiellement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moto modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètre manquant ou mal formaté "),
            @ApiResponse(responseCode = "404", description = "Moto non trouvée pour l'id spécifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<MotoResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody MotoRequestDto motoRequestDto){
        MotoResponseDto response = motoService.modifierPartiellement(id, motoRequestDto);
        return ResponseEntity.ok(response);
    }
}
