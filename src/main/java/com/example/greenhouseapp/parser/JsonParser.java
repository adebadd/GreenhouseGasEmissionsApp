package com.example.greenhouseapp.parser;

import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.repository.EmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;

@Component
public class JsonParser {

    @Autowired
    private EmissionRepository emissionRepository;

    public void parseAndMatchJson(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(filePath);
            Object obj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray emissionsArray = (JSONArray) jsonObject.get("Emissions");

            for (int i = 0; i < emissionsArray.size(); i++) {
                JSONObject emissionObj = (JSONObject) emissionsArray.get(i);

                String category = (String) emissionObj.get("Category");
                String gasUnits = (String) emissionObj.get("Gas Units");
                Number valueNumber = (Number) emissionObj.get("Value");

                if (category != null && gasUnits != null && valueNumber != null) {
                    double value = valueNumber.doubleValue();

                    if (value > 0) {
                        List<Emission> emissions = emissionRepository.findByCategoryAndGasUnits(
                            category, gasUnits
                        );

                        for (Emission emission : emissions) {
                            emission.setActualValue(value);
                            emissionRepository.save(emission);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}