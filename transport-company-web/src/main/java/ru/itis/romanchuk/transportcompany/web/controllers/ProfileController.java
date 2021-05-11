package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.romanchuk.transportcompany.api.services.UserService;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.web.security.details.UserDetailsImpl;

import java.security.Principal;

@Controller
public class ProfileController {

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getView(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        System.out.println(userDetails);
        User user = userService.getUserById(userDetails.getUser().getId());
        model.addAttribute("owner", user);
        return "profile";
    }
}
