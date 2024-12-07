package com.example.greenhouseapp.repository;

import com.example.greenhouseapp.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
    List<Emission> findByCategoryAndYear(String category, int year);
    List<Emission> findByCategoryAndGasUnits(String category, String gasUnits);
}