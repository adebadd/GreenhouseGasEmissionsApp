package com.example.greenhouseapp.parser;

import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.repository.EmissionRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class HtmlParser {

    @Autowired
    private EmissionRepository emissionRepository;

    public void parseHtmlForDescriptions(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.getElementsByTag("table");
            for (Element table : tables) {
                Elements rows = table.getElementsByTag("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cells = row.getElementsByTag("td");
                    if (cells.size() > 6) {
                        String category = cells.get(2).text();
                        String description = cells.get(8).text();
                        List<Emission> emissions = emissionRepository.findAll();
                        for (Emission emission : emissions) {
                            if (emission.getCategory().equalsIgnoreCase(category)) {
                                emission.setDescription(description);
                                emissionRepository.save(emission);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}