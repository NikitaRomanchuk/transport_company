package ru.itis.romanchuk.transportcompany.web.security.provider;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.web.exception.TokenAuthenticationException;
import ru.itis.romanchuk.transportcompany.impl.config.JwtProperties;
import ru.itis.romanchuk.transportcompany.web.security.details.UserDetailsImpl;

import java.util.Collections;
import java.util.Optional;

@Component
public class JwtAuthenticationProvider {

    private final JwtProperties jwtProperties;

    public JwtAuthenticationProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public Optional<Authentication> authenticate(String token) throws AuthenticationException {
        if (StringUtils.hasLength(token)){
            try {
                Jws<Claims> claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
                User user = new User();
                user.setEmail(claims.getBody().getSubject());
                return Optional.of(new UsernamePasswordAuthenticationToken(new UserDetailsImpl(user), "",
                        Collections.singleton(new SimpleGrantedAuthority(claims.getBody().get("role", User.Role.class).toString()))));
            } catch (JwtException | IllegalArgumentException e) {
                throw new TokenAuthenticationException("Unauthorized");
            }

        }
        return Optional.empty();
    }
}