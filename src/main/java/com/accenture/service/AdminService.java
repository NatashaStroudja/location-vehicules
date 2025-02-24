package com.accenture.service;
import com.accenture.exception.AdminException;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.ClientResponseDto;


import java.util.List;

public interface AdminService {
    AdminResponseDto ajouterAdmin(AdminRequestDto adminRequestDto) throws AdminException;
    List<AdminResponseDto> trouverTousAdmins();
    AdminResponseDto trouverByEmailEtPassword(String email, String password);
}
