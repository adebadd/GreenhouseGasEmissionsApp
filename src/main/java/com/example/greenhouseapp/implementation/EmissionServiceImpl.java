package com.example.greenhouseapp.implementation;

import com.example.greenhouseapp.service.EmissionService;
import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.repository.EmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionServiceImpl implements EmissionService {

    private final EmissionRepository emissionRepository;

    public EmissionServiceImpl(EmissionRepository emissionRepository) {
        super();
        this.emissionRepository = emissionRepository;
    }

    @Override
    public Emission saveEmission(Emission emission) {
        return emissionRepository.save(emission);
    }

    @Override
    public List<Emission> getAllEmissions() {
        return emissionRepository.findAll();
    }

    @Override
    public Emission getEmissionById(long id) {
        return emissionRepository.findById(id).get();
    }

    @Override
    public Emission updateEmission(Emission emission, long id) {
        Emission existingEmission = emissionRepository.findById(id).get();
        existingEmission.setCategory(emission.getCategory());
        existingEmission.setScenario(emission.getScenario());
        existingEmission.setYear(emission.getYear());
        existingEmission.setGasUnits(emission.getGasUnits())	;
        existingEmission.setPredictedValue(emission.getPredictedValue());
        existingEmission.setActualValue(emission.getActualValue());
        emissionRepository.save(existingEmission);
        return existingEmission;
    }

    @Override
    public void deleteEmission(long id) {
        emissionRepository.deleteById(id);
    }
}