package com.accenture.service;

import com.accenture.exception.AdminException;
import com.accenture.exception.ClientException;
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
    public static final String ID_NON_PRESENT = "Ce id n'existe pas!";
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

    @Override
    public void supprimer(int id) throws EntityNotFoundException {
        if (adminDao.existsById(id))
            adminDao.deleteById(id);
        else
            throw new EntityNotFoundException(ID_NON_PRESENT);
    }

    @Override
    public AdminResponseDto modifierPartiellement(int id, AdminRequestDto adminRequestDto) throws AdminException {
        Optional<Admin> optAdmin = adminDao.findById(id);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException(ID_NON_PRESENT);
        Admin adminExist = optAdmin.get();//on prend le client trouve
        Admin nouveauAdmin = adminMapper.toAdmin(adminRequestDto);//on cree le nouveau clientRequestDto et on le transforme en nouveau client
        remplacer(adminExist, nouveauAdmin);
        Admin adminEnger = adminDao.save(adminExist);//on enregistre ce client
        return adminMapper.toAdminResponseDto(adminEnger);// on transforme le client enregistre en clientResponseDto
    }

    private static void remplacer(Admin adminExist, Admin nouveauAdmin) {
        if (nouveauAdmin == null)
            throw new AdminException("L'admin ne peut pas etre null");
        if (nouveauAdmin.getNom() != null)
            adminExist.setNom(nouveauAdmin.getNom());
        if (nouveauAdmin.getPrenom() != null)
            adminExist.setPrenom(nouveauAdmin.getPrenom());
        if (nouveauAdmin.getEmail() != null)
            adminExist.setEmail(nouveauAdmin.getEmail());
        if (nouveauAdmin.getPassword() != null)
            adminExist.setPassword(nouveauAdmin.getPassword());
        if (nouveauAdmin.getFonction() != null)
            adminExist.setFonction(nouveauAdmin.getFonction());
    }


    private static void verifierAdmin(AdminRequestDto adminRequestDto) throws AdminException, EntityNotFoundException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$";

        if (adminRequestDto == null)
            throw new AdminException("L'admin est nulle");
        if (adminRequestDto.nom() == null || adminRequestDto.nom().isBlank())
            throw new AdminException("Le nom est obligatoire");
        if (adminRequestDto.prenom() == null || adminRequestDto.prenom().isBlank())
            throw new AdminException("Le prenom est obligatoire");
        if (adminRequestDto.email() == null || adminRequestDto.email().isBlank() || !adminRequestDto.email().matches(emailRegex))
            throw new AdminException("Le email est obligatoire et doit etre ecrit en format mail");
        if (adminRequestDto.password() == null || adminRequestDto.password().isBlank())
            throw new AdminException("Le mot de passe est obligatoite");

        if (!adminRequestDto.password().matches(passwordRegex))
            throw new AdminException("Le mot de passe doit faire entre 8 et 16 caractères, doit comporter obligatoirement au minimum une \n" +
                    "majuscule, une minuscule, un chiffre et un caractère parmi la liste suivante : & # @ - _ §");
        if (adminRequestDto.fonction() == null || adminRequestDto.fonction().isBlank())
        throw new AdminException("La fonction est obligatoire");
    }

}
