package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.romanchuk.transportcompany.api.services.OauthTokenService;
import ru.itis.romanchuk.transportcompany.db.models.OauthToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OauthController {

    private OauthTokenService tokenService;

    public OauthController(OauthTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/oauth")
    public String authorize(@RequestParam String code, HttpServletResponse response){
        OauthToken token;
        try {
            token = tokenService.getToken(code);
        }
        catch (IllegalArgumentException e){
            return "redirect:/login";
        }
        response.addCookie(new Cookie("oauth_token", token.getValue()));
        return "redirect:/profile";
    }
}
