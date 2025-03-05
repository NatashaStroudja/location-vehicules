package com.accenture.repository;


import com.accenture.repository.entity.Location;
import com.accenture.repository.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationDao extends JpaRepository<Location, Long> {

}
