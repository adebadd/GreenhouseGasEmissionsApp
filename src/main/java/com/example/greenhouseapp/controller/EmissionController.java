package com.example.greenhouseapp.controller;

import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.service.EmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emissions")
public class EmissionController {

    private final EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        super();
        this.emissionService = emissionService;
    }
    

    @PostMapping
    public ResponseEntity<Emission> saveEmission(@RequestBody Emission emission) {
        return new ResponseEntity<>(emissionService.saveEmission(emission), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Emission> getAllEmissions() {
        return emissionService.getAllEmissions();
    }

    @GetMapping("{id}")
    public ResponseEntity<Emission> getEmissionById(@PathVariable("id") long id) {
        return new ResponseEntity<>(emissionService.getEmissionById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Emission> updateEmission(@PathVariable("id") long id, @RequestBody Emission emission) {
        return new ResponseEntity<>(emissionService.updateEmission(emission, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmission(@PathVariable("id") long id) {
        emissionService.deleteEmission(id);
        return new ResponseEntity<>("Emission deleted successfully!", HttpStatus.OK);
    }
    
}