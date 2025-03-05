package com.accenture.controller;

import com.accenture.service.ClientService;
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
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Ajouter un nouveau client", description = "Ajouter un nouveau client à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client est créé avec succès"),
            @ApiResponse(responseCode = "400", description = "La requête est invalide")
    })
    ResponseEntity<Void> ajouter(@Valid @RequestBody ClientRequestDto clientRequestDto) {
       ClientResponseDto clientEnreg =  clientService.ajouterClient(clientRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientEnreg.id())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Afficher la liste des clients")
    List<ClientResponseDto> listerDesClients() {
        return clientService.trouverTousClients();
    }

    @GetMapping("/infos")
    @Operation(summary = "Afficher un client par mail et mots de pass")
    ResponseEntity<ClientResponseDto> clientTrouverParEmailEtPassword(
        @RequestParam String email,
        @RequestParam String password){
        ClientResponseDto trouve = clientService.trouverByEmailEtPassword(email, password);
        return ResponseEntity.ok(trouve);
    }
    @DeleteMapping ("/{id}")
    @Operation(summary = "Supprimer un client par id")
    ResponseEntity<Void> supprimer(@PathVariable("id") int id){
        clientService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Modifier un client partiellement")
    ResponseEntity<ClientResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody ClientRequestDto clientRequestDto){
        ClientResponseDto response = clientService.modifierPartiellement(id, clientRequestDto);
        return ResponseEntity.ok(response);
    }
}
