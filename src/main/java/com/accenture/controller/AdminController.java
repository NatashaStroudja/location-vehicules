package com.accenture.controller;

import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
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
 * Contrôleur pour la gestion des administrateurs.
 * Ce contrôleur permet de l'ajout, la suppression, la modification, et la récupération des administrateurs.
 */
@RestController
@RequestMapping("/admins")
@Tag(name = "Administrateurs", description = "Gestion des administrateurs")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Cette méthode permet d'ajouter un nouvel administrateur à la base de données.
     *
     * @param adminRequestDto les informations de l'administrateur à ajouter
     * @return une réponse HTTP avec un statut 201 (Created) si l'administrateur est ajouté avec succès
     */

    @PostMapping
    @Operation(summary = "Ajouter un nouveau administrateur", description = "Ajouter un nouveau admin à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin est créé avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto adminEnreg = adminService.ajouterAdmin(adminRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adminEnreg.id())
                .toUri();
        log.info("Administrateur ajouté avec succès, ID : {}", adminEnreg.id());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cette méthode permet d'afficher la liste de tous les administrateurs enregistrés.
     *
     * @return une liste de DTO représentant les administrateurs
     */
    @GetMapping
    @Operation(summary = "Afficher les administrateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des administrateurs récupérée avec succès")
    })
    List<AdminResponseDto> listerDesAdmins() {
        log.info("Administrateurs affichés avec succès");
        return adminService.trouverTousAdmins();
    }

    /**
     * Cette méthode permet de récupérer un administrateur en utilisant son email et mot de passe.
     *
     * @param email    l'email de l'administrateur
     * @param password le mot de passe de l'administrateur
     * @return l'administrateur trouvé
     */
    @GetMapping("/infos")
    @Operation(summary = "Afficher un admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin trouvé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide,email ou mot de passe mal formatés "),
            @ApiResponse(responseCode = "404", description = "Admin non trouvé pour les identifiants fournis"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<AdminResponseDto> trouverParEmailEtPassword(
            @RequestParam String email,
            @RequestParam String password) {
        AdminResponseDto trouve = adminService.trouverByEmailEtPassword(email, password);
        log.info("L'administrateur affiché avec succès");
        return ResponseEntity.ok(trouve);
    }

    /**
     * Cette méthode permet de supprimer un administrateur de la base de données par son ID.
     *
     * @param id l'identifiant de l'administrateur à supprimer
     * @return une réponse HTTP avec un statut 204 (No Content) si la suppression a réussi
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un admin par id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "L'id n'est pas trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, l'ID est mal formaté ou manquant"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors de la suppression")
    })
    ResponseEntity<Void> supprimer(@PathVariable("id") int id) {
        adminService.supprimer(id);
        log.info("Administrateur supprimé avec succès");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /**
     * Cette méthode permet de modifier certains attributs d'un administrateur existant.
     *
     * @param id l'identifiant de l'administrateur à modifier
     * @param adminRequestDto les informations à mettre à jour pour l'administrateur
     * @return l'administrateur mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier un admin partiellement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètre manquant ou mal formaté "),
            @ApiResponse(responseCode = "404", description = "Admin non trouvé pour l'id spécifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<AdminResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto reponse = adminService.modifierPartiellement(id, adminRequestDto);
        log.info("Administrateur modifié avec succès");
        return ResponseEntity.ok(reponse);
    }
}
