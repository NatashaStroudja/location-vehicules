package com.accenture.repository;


import com.accenture.repository.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de gestion des opérations CRUD pour les entités {@link Location}.
 * Cette interface étend {@link JpaRepository} pour fournir des opérations de persistance.
 * Elle permet de gérer les données liées aux locations.
 */
public interface LocationDao extends JpaRepository<Location, Long> {

}
