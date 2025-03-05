package com.accenture.controller;

import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
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
@RequestMapping("/admins")
@Tag(name = "Administrateurs", description = "Gestion des administrateurs")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @Operation(summary = "Ajouter un nouveau administrateur", description = "Ajouter un nouveau admin à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin est créé avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto adminEnreg = adminService.ajouterAdmin(adminRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adminEnreg.id())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Afficher les administrateurs")
    List<AdminResponseDto> listerDesAdmins() {
        return adminService.trouverTousAdmins();
    }

    @GetMapping("/infos")
    @Operation(summary = "Afficher un admin par id")
    ResponseEntity<AdminResponseDto> adminTrouverParEmailEtPassword(
            @RequestParam String email,
            @RequestParam String password){
        AdminResponseDto trouve = adminService.trouverByEmailEtPassword(email, password);
        return ResponseEntity.ok(trouve);
    }
    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer un admin par id")
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        adminService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier un admin partiellement")
    ResponseEntity<AdminResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody AdminRequestDto adminRequestDto){
        AdminResponseDto reponse = adminService.modifierPartiellement(id, adminRequestDto);
        return ResponseEntity.ok(reponse);
    }
}
