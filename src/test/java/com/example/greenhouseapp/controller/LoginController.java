package com.example.greenhouseapp.controller;

import com.example.greenhouseapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, ModelMap model, HttpSession session) {
        if (loginService.authenticate(name, password)) {
            session.setAttribute("name", name);
            model.put("name", name);
            return "redirect:/home";
        } else {
            model.put("errorMessage", "Invalid name or password");
            return "login";
        }
    }
}