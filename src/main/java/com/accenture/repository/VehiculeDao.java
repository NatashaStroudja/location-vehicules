package com.accenture.repository;

import com.accenture.repository.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface de gestion des opérations CRUD pour les entités {@link Vehicule}.
 * Cette interface étend {@link JpaRepository} pour fournir des opérations de persistance.
 * Elle permet de gérer les données liées aux véhicules.
 *
 * @author Natasha
 */
public interface VehiculeDao extends JpaRepository<Vehicule, Integer> {
}
