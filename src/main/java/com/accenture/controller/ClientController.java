package com.accenture.controller;

import com.accenture.service.ClientService;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    ResponseEntity<Void> ajouter(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        clientService.ajouterClient(clientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    List<ClientResponseDto> listeDesClients() {
        return clientService.trouverTousClients();
    }
    @GetMapping("/infos")
    ResponseEntity<ClientResponseDto> clientTrouveParEmailEtPassword(
        @RequestParam String email,
        @RequestParam String password){
        ClientResponseDto trouve = clientService.trouverByEmailEtPassword(email, password);
        return ResponseEntity.ok(trouve);
    }
    @DeleteMapping ("/{id}")
    ResponseEntity<Void> suppr(@PathVariable("id") int id){
        clientService.supprimer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/{id}")
    ResponseEntity<ClientResponseDto> modifierPartiellement(@PathVariable("id") int id, @RequestBody ClientRequestDto clientRequestDto){
        ClientResponseDto reponse = clientService.modifierPartiellement(id, clientRequestDto);
        return ResponseEntity.ok(reponse);
    }
}
