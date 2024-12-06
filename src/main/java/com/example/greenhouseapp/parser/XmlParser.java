package com.example.greenhouseapp.parser;

import com.example.greenhouseapp.model.Emission;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public List<Emission> parseEmissions(String filePath) {
        List<Emission> emissions = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList rows = doc.getElementsByTagName("Row");

            for (int i = 0; i < rows.getLength(); i++) {
                Node row = rows.item(i);

                System.out.println("\nCurrent Element: " + row.getNodeName());

                if (row.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) row;

                    Node categoryNode = elem.getElementsByTagName("Category__1_3").item(0);
                    Node scenarioNode = elem.getElementsByTagName("Scenario").item(0);
                    Node yearNode = elem.getElementsByTagName("Year").item(0);
                    Node valueNode = elem.getElementsByTagName("Value").item(0);
                    Node gasUnitsNode = elem.getElementsByTagName("Gas___Units").item(0);

                    if (categoryNode == null || scenarioNode == null || yearNode == null || valueNode == null) {
                        continue;
                    }

                    try {
                        String category = categoryNode.getTextContent();
                        String scenario = scenarioNode.getTextContent();
                        int year = Integer.parseInt(yearNode.getTextContent());
                        double value = Double.parseDouble(valueNode.getTextContent());
                        String gasUnits = gasUnitsNode != null ? gasUnitsNode.getTextContent() : "";

                        if ("WEM".equalsIgnoreCase(scenario) && year == 2023 && value > 0) {
                            Emission emission = new Emission();
                            emission.setCategory(category);
                            emission.setScenario(scenario);
                            emission.setYear(year);
                            emission.setPredictedValue(value);
                            emission.setActualValue(0.0);
                            emission.setGasUnits(gasUnits);

                            emissions.add(emission);

                            System.out.println("Parsed Emission: " + emission);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emissions;
    }
}