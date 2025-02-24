package com.accenture.controller;

import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.ClientResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    ResponseEntity<Void> ajoute(@Valid @RequestBody AdminRequestDto adminRequestDto) {
        adminService.ajouterAdmin(adminRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    List<AdminResponseDto> listeDesAdmins() {
        return adminService.trouverTousAdmins();
    }

    @GetMapping("/infos")
    ResponseEntity<AdminResponseDto> adminTrouveParEmailEtPassword(
            @RequestParam String email,
            @RequestParam String password){
        AdminResponseDto trouve = adminService.trouverByEmailEtPassword(email, password);
        return ResponseEntity.ok(trouve);
    }
}
