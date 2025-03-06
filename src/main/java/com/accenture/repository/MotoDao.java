package com.accenture.repository;

import com.accenture.repository.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface de gestion des opérations CRUD pour les entités {@link Moto}.
 * Cette interface étend {@link JpaRepository} pour fournir des opérations de persistance.
 * Elle permet de gérer les données liées aux motos.
 *
 * @author Natasha
 */
public interface MotoDao extends JpaRepository<Moto, Integer> {
}
