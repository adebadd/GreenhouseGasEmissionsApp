package com.example.greenhouseapp.repository;

import com.example.greenhouseapp.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
}