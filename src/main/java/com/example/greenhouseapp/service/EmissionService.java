package com.example.greenhouseapp.service;

import com.example.greenhouseapp.model.Emission;
import java.util.List;

public interface EmissionService {
    Emission saveEmission(Emission emission);
    List<Emission> getAllEmissions();
    Emission getEmissionById(long id);
    Emission updateEmission(Emission emission, long id);
    void deleteEmission(long id);
}