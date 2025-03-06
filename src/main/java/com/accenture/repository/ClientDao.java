package com.accenture.repository;


import com.accenture.repository.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de gestion des opérations CRUD pour les entités {@link Client}.
 * Cette interface étend {@link JpaRepository} pour fournir des opérations de persistance.
 */
public interface ClientDao extends JpaRepository <Client, Integer> {
    /**
     * Recherche un client en fonction de son adresse email et de son mot de passe.
     *
     * @param email l'adresse email du client
     * @param password le mot de passe du client
     * @return un {@link Optional} contenant le client trouvé, ou un {@link Optional#empty()} si aucun client n'a été trouvé.
     */
    Optional<Client> findByEmailAndPassword(String email, String password);
}
