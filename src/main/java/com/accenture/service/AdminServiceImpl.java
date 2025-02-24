package com.accenture.service;

import com.accenture.exception.AdminException;
import com.accenture.repository.AdminDao;
import com.accenture.repository.entity.Admin;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.mapper.AdminMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminDao adminDao, AdminMapper adminMapper) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminResponseDto ajouterAdmin(AdminRequestDto adminRequestDto) throws AdminException {
        verifierAdmin(adminRequestDto);
        Admin admin = adminMapper.toAdmin(adminRequestDto);
        Admin clientRetour = adminDao.save(admin);

        return adminMapper.toAdminResponseDto(admin);

    }

    @Override
    public List<AdminResponseDto> trouverTousAdmins() {
        return adminDao.findAll().stream()
                .map(adminMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    public AdminResponseDto trouverByEmailEtPassword(String email, String password) throws EntityNotFoundException {
        Optional<Admin> optAdmin = adminDao. findByEmailAndPassword(email, password);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException("Cet administrateur n'existe pas");
        Admin admin = optAdmin.get();
        return adminMapper.toAdminResponseDto(admin);
    }

    private static void verifierAdmin(AdminRequestDto adminRequestDto) throws AdminException, EntityNotFoundException {
        if (adminRequestDto == null)
            throw new AdminException("Le admin est nulle");
        if (adminRequestDto.nom() == null || adminRequestDto.nom().isBlank())
            throw new AdminException("Le nom est obligatoire");
        if (adminRequestDto.prenom() == null || adminRequestDto.prenom().isBlank())
            throw new AdminException("Le prenom est obligatoire");
        if (adminRequestDto.email() == null || adminRequestDto.email().isBlank())
            throw new AdminException("Le email est obligatoire");
        if (adminRequestDto.password() == null || adminRequestDto.password().isBlank())
            throw new AdminException("Le mot de passe est obligatoite");
        if (adminRequestDto.password() == null || adminRequestDto.password().isBlank())
            throw new AdminException("Le mot de passe est obligatoite");
        if (adminRequestDto.fonction() == null || adminRequestDto.fonction().isBlank());
    }

}
