package com.example.greenhouseapp.controller;

import com.example.greenhouseapp.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String password, ModelMap model) {
        try {
            registerService.registerUser(name, password);
            model.put("name", name);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            model.put("errorMessage", e.getMessage());
            return "register";
        } catch (Exception e) {
            return "error";
        }
    }
}