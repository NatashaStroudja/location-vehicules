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

@RestController
@RequestMapping("/voitures")
@Tag(name = "Voitures", description = "Gestion des voitures")
public class VoitureController {
    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @PostMapping
    @Operation(summary = "Ajouter une nouvelle voiture", description = "Ajouter une nouvelle voiture à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voiture est créée avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide")
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

    @GetMapping
    @Operation(summary = "Afficher la liste des voitures")
    List<VoitureResponseDto> listerDesVoitures() {
        return voitureService.trouverToutesVoitures();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Afficher une voiture par id")
    ResponseEntity<VoitureResponseDto> afficherVoiturePourAdmin(@PathVariable("id") int id) {
        VoitureResponseDto voitureTrouve = voitureService.trouver(id);
        return ResponseEntity.ok(voitureTrouve);
    }

    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer une voiture par id")
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        voitureService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Modifier une voiture partiellement")
    ResponseEntity<VoitureResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody VoitureRequestDto voitureRequestDto){
        VoitureResponseDto response = voitureService.modifierPartiellement(id, voitureRequestDto);
        return ResponseEntity.ok(response);
    }
}
