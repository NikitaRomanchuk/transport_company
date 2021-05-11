package ru.itis.romanchuk.transportcompany.web.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.romanchuk.transportcompany.api.dto.JwtTokenDto;
import ru.itis.romanchuk.transportcompany.api.services.JwtTokenService;

@RestController
public class JwtController {
    private final JwtTokenService jwtTokenService;

    public JwtController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity<JwtTokenDto> getToken(@RequestBody String login, @RequestBody String password){
        return ResponseEntity.ok(new JwtTokenDto(jwtTokenService.getToken(login,password)));
    }
}
