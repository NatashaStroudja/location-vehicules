package com.accenture.controller;

import com.accenture.service.ClientService;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
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
 * Contrôleur pour la gestion des clients.
 * Ce contrôleur permet de l'ajout, la suppression, la modification, et la récupération des clients.
 */
@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Cette méthode permet d'ajouter un nouvel client à la base de données.
     *
     * @param clientRequestDto les informations du client à ajouter
     * @return une réponse HTTP avec un statut 201 (Created) si le client est ajouté avec succès
     */
    @PostMapping
    @Operation(summary = "Ajouter un nouveau client", description = "Ajouter un nouveau client à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        ClientResponseDto clientEnreg = clientService.ajouterClient(clientRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientEnreg.id())
                .toUri();
        log.info("Client ajouté avec succès");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cette méthode permet d'afficher la liste de tous les clients enregistrés.
     *
     * @return une liste de DTO représentant les clients
     */
    @GetMapping
    @Operation(summary = "Afficher la liste des clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des clients récupérée avec succès")
    })
    List<ClientResponseDto> listerDesClients() {
        log.info("Affichage des clients");
        return clientService.trouverTousClients();
    }

    /**
     * Cette méthode permet de récupérer un client en utilisant son email et mot de passe.
     *
     * @param email    l'email du client
     * @param password le mot de passe du client
     * @return client trouvé
     */
    @GetMapping("/infos")
    @Operation(summary = "Afficher un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide,email ou mot de passe mal formatés "),
            @ApiResponse(responseCode = "404", description = "Client non trouvé pour les identifiants fournis"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<ClientResponseDto> afficherClient(
            @RequestParam String email,
            @RequestParam String password) {
        ClientResponseDto trouve = clientService.trouverByEmailEtPassword(email, password);
        log.info("Le client est trouvé avec succès");
        return ResponseEntity.ok(trouve);
    }

    /**
     * Cette méthode permet de supprimer un client de la base de données par son ID.
     *
     * @param id l'identifiant du client à supprimer
     * @return une réponse HTTP avec un statut 204 (No Content) si la suppression a réussi
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client par id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "L'id n'est pas trouvé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, l'ID est mal formaté ou manquant"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur lors de la suppression")
    })
    ResponseEntity<Void> supprimer(@PathVariable("id") int id) {
        clientService.supprimer(id);
        log.info("Le client bien supprimé");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Cette méthode permet de modifier certains attributs d'un administrateur existant.
     *
     * @param id               l'identifiant du client à modifier
     * @param clientRequestDto les informations à mettre à jour pour le client
     * @return client mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier un client partiellement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètre manquant ou mal formaté "),
            @ApiResponse(responseCode = "404", description = "Client non trouvé pour l'id spécifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    ResponseEntity<ClientResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody ClientRequestDto clientRequestDto) {
        ClientResponseDto response = clientService.modifierPartiellement(id, clientRequestDto);
        log.info("Le client modifié avec succès");
        return ResponseEntity.ok(response);
    }
}
