package com.accenture.repository;

import com.accenture.repository.entity.Vehicule;
import com.accenture.repository.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeDao extends JpaRepository<Vehicule, Integer> {
}
