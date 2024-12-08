package com.example.greenhouseapp.controller;

import jakarta.servlet.http.HttpSession;
import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.parser.XmlParser;
import com.example.greenhouseapp.parser.JsonParser;
import com.example.greenhouseapp.service.EmissionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final EmissionService emissionService;
    private final XmlParser xmlParser;
    private final JsonParser jsonParser;

    public HomeController(EmissionService emissionService, XmlParser xmlParser, JsonParser jsonParser) {
        super();
        this.emissionService = emissionService;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        String name = (String) session.getAttribute("name");
        if (name != null) {
            model.addAttribute("name", name);

            List<Emission> emissions = emissionService.getAllEmissions();
            model.addAttribute("emissions", emissions);

            return "home";
        } else {
            return "redirect:/welcome";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/parse")
    public String parseData(HttpSession session, Model model) {
        try {
            List<Emission> emissions = xmlParser.parseEmissions("src/main/resources/data/predicted-emissions.xml");
            emissions.forEach(emissionService::saveEmission);

            jsonParser.parseAndMatchJson("src/main/resources/data/actual-predictions.json");

            List<Emission> updatedEmissions = emissionService.getAllEmissions();
            model.addAttribute("emissions", updatedEmissions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = (String) session.getAttribute("name");
        if (name != null) {
            model.addAttribute("name", name);
        }

        return "home";
    }
}