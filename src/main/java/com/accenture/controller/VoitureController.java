package com.accenture.controller;

import com.accenture.service.VoitureService;
import com.accenture.service.dto.VoitureRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voitures")
public class VoitureController {
    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @PostMapping
    ResponseEntity<Void> ajouter (@Valid @RequestBody VoitureRequestDto voitureRequestDto){
        voitureService.ajouterVoiture(voitureRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
}
}
