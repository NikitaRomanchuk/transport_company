package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.romanchuk.transportcompany.api.dto.forms.RegisterForm;
import ru.itis.romanchuk.transportcompany.api.exceptions.EmailOccupiedException;
import ru.itis.romanchuk.transportcompany.api.services.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getView(Model model){
        RegisterForm form = new RegisterForm();
        model.addAttribute("userForm", form);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm form, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("userForm", form);
            return "register";
        }
        try {
            userService.register(form);
        }catch (EmailOccupiedException e){
            model.addAttribute("error", true);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("userForm", form);
            return "register";
        }
        return "redirect:/login" ;
    }
}
