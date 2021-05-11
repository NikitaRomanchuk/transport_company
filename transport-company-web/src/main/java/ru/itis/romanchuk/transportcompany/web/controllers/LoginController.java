package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Value("${oauth.github.client-id}")
    private String clientId;


    @GetMapping("/login")
    public String getView(Model model){
        model.addAttribute("clientId", clientId);
        return "login";
    }
}
