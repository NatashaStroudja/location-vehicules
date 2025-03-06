package com.accenture.repository;

import com.accenture.repository.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface de gestion des opérations CRUD pour les entités {@link Voiture}.
 * Cette interface étend {@link JpaRepository} pour fournir des opérations de persistance.
 * Elle permet de gérer les données liées aux voitures.
 */
public interface VoitureDao extends JpaRepository<Voiture, Integer> {
}
