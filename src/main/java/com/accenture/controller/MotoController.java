package com.accenture.controller;


import com.accenture.service.MotoService;
import com.accenture.service.dto.MotoRequestDto;
import com.accenture.service.dto.MotoResponseDto;

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
@RequestMapping("/motos")
@Tag(name = "Motos", description = "Gestion des motos")
public class MotoController {
    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping
    @Operation(summary = "Ajouter une nouvelle moto", description = "Ajouter une nouvelle moto à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Moto créée avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide")
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

    @GetMapping
    @Operation(summary = "Afficher la liste des motos")
    List<MotoResponseDto> lister() {
        return motoService.trouverToutes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Afficher une moto par id")
    ResponseEntity<MotoResponseDto> afficher(@PathVariable("id") int id) {
        MotoResponseDto motoTrouve = motoService.trouver(id);
        return ResponseEntity.ok(motoTrouve);
    }

    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer une moto par id")
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        motoService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Modifier une moto partiellement")
    ResponseEntity<MotoResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody MotoRequestDto motoRequestDto){
        MotoResponseDto response = motoService.modifierPartiellement(id, motoRequestDto);
        return ResponseEntity.ok(response);
    }
}
