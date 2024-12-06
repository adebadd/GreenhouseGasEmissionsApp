package com.example.greenhouseapp.controller;

import jakarta.servlet.http.HttpSession;
import com.example.greenhouseapp.model.Emission;
import com.example.greenhouseapp.parser.XmlParser;
import com.example.greenhouseapp.repository.EmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmissionRepository emissionRepository;

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        String name = (String) session.getAttribute("name");
        if (name != null) {
            model.addAttribute("name", name);

            List<Emission> emissions = emissionRepository.findAll();
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
            XmlParser parser = new XmlParser();
            List<Emission> emissions = parser.parseEmissions("src/main/resources/data/predicted-emissions.xml");

            emissionRepository.saveAll(emissions);

            model.addAttribute("message", "Data parsed and saved successfully!");
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