package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.services.JwtTokenService;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.db.repositories.UserRepository;
import io.jsonwebtoken.*;
import ru.itis.romanchuk.transportcompany.impl.config.JwtProperties;

import java.util.Date;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProperties jwtProperties;


    public JwtTokenServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProperties jwtProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String getToken(String login, String password) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException(login + "not found"));
        if(!passwordEncoder.matches(password, user.getHashPassword())){
            throw new IllegalArgumentException();
        }
        return createToken(user);
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getValidity());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

    }
}
