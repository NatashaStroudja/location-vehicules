
package com.accenture.service;
import com.accenture.exception.AdminException;
import com.accenture.exception.ClientException;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;
/**
 * Interface de service pour gérer les administrateurs.
 * Cette interface définit les opérations principales que le service administrateur doit fournir,
 * telles que l'ajout, la récupération, la modification et la suppression des administrateurs.
 * Les exceptions spécifiques telles que {@link AdminException} et {@link EntityNotFoundException} sont lancées
 * lors de certaines opérations pour gérer les erreurs d'exécution.
 */
public interface AdminService {
    /**
     * Ajoute un nouvel administrateur dans le système.
     * Cette méthode permet d'ajouter un administrateur en utilisant les informations contenues dans
     * l'objet {@link AdminRequestDto}. Si l'ajout est réussi, un objet {@link AdminResponseDto}
     * contenant les détails de l'administrateur créé est renvoyé. En cas d'erreur, une exception de type
     * {@link AdminException} peut être lancée pour indiquer un problème lors de la création.
     *
     * @param adminRequestDto L'objet contenant les informations nécessaires pour créer un administrateur,
     *                        telles que le nom, le prénom, l'email et d'autres informations pertinentes.
     * @return Un objet {@link AdminResponseDto} contenant les détails de l'administrateur nouvellement créé.
     * @throws AdminException Si une erreur survient lors de la création de l'administrateur, par exemple,
     *                        des informations invalides, un problème de validation ou d'intégrité des données.
     */
    AdminResponseDto ajouterAdmin(AdminRequestDto adminRequestDto) throws AdminException;
    /**
     * Récupère tous les administrateurs du système.
     *
     * Cette méthode retourne une liste de tous les administrateurs enregistrés dans le système,
     * représentée par des objets {@link AdminResponseDto}. Ces objets contiennent les informations
     * essentielles sur chaque administrateur, telles que leur nom, prénom, email, etc.
     *
     * @return Une liste de {@link AdminResponseDto} représentant tous les administrateurs présents dans le système.
     */
    List<AdminResponseDto> trouverTousAdmins();
    /**
     * Récupère un administrateur en fonction de son email et de son mot de passe.
     *
     * Cette méthode permet de retrouver un administrateur unique basé sur les informations d'email
     * et de mot de passe fournies. Si l'email et le mot de passe correspondent à un administrateur
     * existant dans le système, un objet {@link AdminResponseDto} contenant les détails de cet administrateur
     * est renvoyé. Sinon, la méthode pourrait retourner `null` ou lancer une exception en fonction de l'implémentation.
     *
     * @param email L'email de l'administrateur à rechercher. Il doit être unique pour chaque administrateur.
     * @param password Le mot de passe de l'administrateur pour valider l'identité.
     * @return Un objet {@link AdminResponseDto} représentant l'administrateur trouvé, contenant les informations de celui-ci.
     */
    AdminResponseDto trouverByEmailEtPassword(String email, String password);
    /**
     * Supprime un administrateur du système en fonction de son identifiant.
     *
     * Cette méthode permet de supprimer un administrateur du système. Si un administrateur avec
     * l'identifiant spécifié existe, il est supprimé de la base de données. Si aucun administrateur
     * avec cet identifiant n'est trouvé, une exception {@link EntityNotFoundException} est levée.
     *
     * @param id L'identifiant unique de l'administrateur à supprimer.
     * @throws EntityNotFoundException Si l'administrateur avec l'identifiant spécifié n'est pas trouvé dans le système.
     */
    void supprimer(int id) throws EntityNotFoundException;
    /**
     * Modifie partiellement un administrateur dans le système.
     *
     * Cette méthode permet de modifier certains attributs d'un administrateur existant dans le système
     * à partir des informations fournies dans {@link AdminRequestDto}. Seules les informations présentes
     * dans l'objet `adminRequestDto` seront mises à jour, ce qui permet une modification partielle de l'administrateur.
     * Si l'administrateur avec l'identifiant spécifié n'est pas trouvé ou si une erreur survient lors de la modification,
     * une exception {@link AdminException} peut être levée.
     *
     * @param id L'identifiant unique de l'administrateur à modifier.
     * @param adminRequestDto L'objet contenant les informations mises à jour pour l'administrateur. Seules les informations
     *                        présentes dans cet objet seront modifiées.
     * @return Un objet {@link AdminResponseDto} représentant l'administrateur après la modification, contenant les informations
     *         mises à jour.
     * @throws AdminException Si une erreur survient lors de la modification de l'administrateur, par exemple, si l'administrateur
     *                        n'existe pas ou si les données fournies sont invalides.
     */
    AdminResponseDto modifierPartiellement(int id, AdminRequestDto adminRequestDto) throws AdminException;
}

