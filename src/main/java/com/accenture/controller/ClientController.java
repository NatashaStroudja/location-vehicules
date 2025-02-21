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
    @GetMapping("/{email}")
    ResponseEntity<ClientResponseDto> clientTrouve(@PathVariable("email") String email){
        ClientResponseDto trouve = clientService.trouverByEmail(email);
        return ResponseEntity.ok(trouve);
    }
}
