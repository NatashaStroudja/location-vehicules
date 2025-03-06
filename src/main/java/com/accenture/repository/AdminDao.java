package com.accenture.repository;

import com.accenture.repository.entity.Admin;
import com.accenture.repository.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de gestion des opérations CRUD(create, read, update, delete) pour les entités {@link Admin}.
 * Cette interface étend JpaRepository pour fournir des opérations de persistance(stocker, récupérer, mettre à jour ou supprimer des données dans un système de gestion de base de données ).
 *
 */
public interface AdminDao extends JpaRepository<Admin, Integer> {
/**
 * Recherche un administrateur en fonction de son adresse email et de son mot de passe.
 *
 * @param email l'adresse email de l'administrateur
 * @param password le mot de passe de l'administrateur
 * @return un {@link Optional} contenant l'administrateur trouvé, ou un {@link Optional#empty()} si aucun administrateur n'a été trouvé.
 */
    Optional<Admin> findByEmailAndPassword(String email, String password);
}
