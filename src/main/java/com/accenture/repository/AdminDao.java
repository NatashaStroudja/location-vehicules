package com.accenture.repository;

import com.accenture.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {
}
