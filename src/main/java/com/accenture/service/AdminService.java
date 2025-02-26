
package com.accenture.service;
import com.accenture.exception.AdminException;
import com.accenture.exception.ClientException;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;

public interface AdminService {
    AdminResponseDto ajouterAdmin(AdminRequestDto adminRequestDto) throws AdminException;
    List<AdminResponseDto> trouverTousAdmins();

    AdminResponseDto trouverByEmailEtPassword(String email, String password);
    void supprimer(int id) throws EntityNotFoundException;
    AdminResponseDto modifierPartiellement(int id, AdminRequestDto adminRequestDto) throws AdminException;
}

