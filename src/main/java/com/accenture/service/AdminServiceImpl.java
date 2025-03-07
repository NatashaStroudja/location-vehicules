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
/**
 * Implémentation du service de gestion des administrateurs.
 *
 * Cette classe fournit les opérations de gestion des administrateurs, y compris l'ajout, la suppression, la mise à jour
 * partielle, ainsi que la récupération des administrateurs par email et mot de passe. Elle utilise un DAO pour interagir
 * avec la base de données et un Mapper pour convertir entre les entités et les DTOs.
 */
@Service
public class AdminServiceImpl implements AdminService {
    public static final String ID_NON_PRESENT = "Ce id n'existe pas!";
    private final AdminDao adminDao;
    private final AdminMapper adminMapper;
    /**
     * Constructeur de l'implémentation du service Admin.
     *
     * @param adminDao L'interface de gestion des données des administrateurs.
     * @param adminMapper Le mapper pour convertir entre les entités Admin et les DTOs.
     */
    public AdminServiceImpl(AdminDao adminDao, AdminMapper adminMapper) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
    }
    /**
     * Ajoute un administrateur au système.
     *
     * Cette méthode crée un nouvel administrateur en vérifiant les données et en les enregistrant dans la base de données.
     * Un DTO représentant l'administrateur ajouté est renvoyé.
     *
     * @param adminRequestDto Le DTO contenant les informations de l'administrateur à ajouter.
     * @return Un DTO {@link AdminResponseDto} représentant l'administrateur créé.
     * @throws AdminException Si les données fournies sont invalides ou si une erreur survient lors de l'ajout.
     */
    @Override
    public AdminResponseDto ajouterAdmin(AdminRequestDto adminRequestDto) throws AdminException {
        verifierAdmin(adminRequestDto);
        Admin admin = adminMapper.toAdmin(adminRequestDto);
        Admin clientRetour = adminDao.save(admin);

        return adminMapper.toAdminResponseDto(admin);

    }
    /**
     * Récupère tous les administrateurs du système.
     *
     * Cette méthode renvoie une liste de DTOs {@link AdminResponseDto} représentant tous les administrateurs dans le système.
     *
     * @return Une liste de DTOs {@link AdminResponseDto} représentant tous les administrateurs.
     */
    @Override
    public List<AdminResponseDto> trouverTousAdmins() {
        return adminDao.findAll().stream()
                .map(adminMapper::toAdminResponseDto)
                .toList();
    }
    /**
     * Recherche un administrateur par son email et mot de passe.
     *
     * Cette méthode permet de récupérer un administrateur en utilisant son email et son mot de passe.
     * Si aucun administrateur n'est trouvé, une exception {@link EntityNotFoundException} est levée.
     *
     * @param email L'email de l'administrateur.
     * @param password Le mot de passe de l'administrateur.
     * @return Un DTO {@link AdminResponseDto} représentant l'administrateur trouvé.
     * @throws EntityNotFoundException Si l'administrateur avec cet email et mot de passe n'est pas trouvé.
     */
    @Override
    public AdminResponseDto trouverByEmailEtPassword(String email, String password) throws EntityNotFoundException {
        Optional<Admin> optAdmin = adminDao. findByEmailAndPassword(email, password);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException("Cet administrateur n'existe pas");
        Admin admin = optAdmin.get();
        return adminMapper.toAdminResponseDto(admin);
    }
    /**
     * Supprime un administrateur en fonction de son identifiant.
     *
     * Cette méthode supprime un administrateur du système en utilisant son identifiant. Si l'administrateur n'existe pas,
     * une exception {@link EntityNotFoundException} est levée.
     *
     * @param id L'identifiant unique de l'administrateur à supprimer.
     * @throws EntityNotFoundException Si l'administrateur avec cet identifiant n'est pas trouvé.
     */
    @Override
    public void supprimer(int id) throws EntityNotFoundException {
        if (adminDao.existsById(id))
            adminDao.deleteById(id);
        else
            throw new EntityNotFoundException(ID_NON_PRESENT);
    }

    /**
     * Modifie partiellement un administrateur.
     *
     * Cette méthode permet de mettre à jour partiellement un administrateur en fonction de l'identifiant et des nouvelles
     * informations fournies dans {@link AdminRequestDto}. Si l'administrateur n'est pas trouvé, une exception
     * {@link EntityNotFoundException} est levée.
     *
     * @param id L'identifiant unique de l'administrateur à modifier.
     * @param adminRequestDto Le DTO contenant les informations à mettre à jour.
     * @return Un DTO {@link AdminResponseDto} représentant l'administrateur après modification.
     * @throws AdminException Si une erreur survient lors de la modification (par exemple, données invalides).
     */
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
    /**
     * Remplace les informations de l'administrateur existant par celles du nouvel administrateur.
     *
     * Cette méthode est utilisée pour remplacer les informations de l'administrateur existant avec celles fournies dans
     * le nouvel administrateur, tout en conservant les autres attributs non modifiés.
     *
     * @param adminExist L'administrateur existant à mettre à jour.
     * @param nouveauAdmin Le nouvel administrateur contenant les données mises à jour.
     */
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
    /**
     * Vérifie les données d'un administrateur avant de l'ajouter ou de le modifier.
     *
     * Cette méthode valide les informations contenues dans {@link AdminRequestDto} pour s'assurer qu'elles sont valides
     * avant de procéder à l'ajout ou à la modification de l'administrateur.
     *
     * @param adminRequestDto Le DTO contenant les informations de l'administrateur à valider.
     * @throws AdminException Si les données sont invalides (par exemple, format de l'email incorrect, mot de passe invalide).
     * @throws EntityNotFoundException Si l'administrateur n'existe pas ou si les données sont incohérentes.
     */

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
