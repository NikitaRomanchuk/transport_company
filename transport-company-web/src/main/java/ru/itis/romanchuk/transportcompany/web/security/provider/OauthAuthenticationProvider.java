package ru.itis.romanchuk.transportcompany.web.security.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.romanchuk.transportcompany.api.services.OauthTokenService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class OauthAuthenticationProvider {

    private final OauthTokenService oauthTokenService;
    private final UserDetailsService userDetailsService;

    public OauthAuthenticationProvider(OauthTokenService oauthTokenService,@Qualifier("customUserDetailService") UserDetailsService userDetailsService) {
        this.oauthTokenService = oauthTokenService;
        this.userDetailsService = userDetailsService;
    }

    public Optional<Authentication> authenticate(String token) throws AuthenticationException {
        return oauthTokenService.getUserByToken(token).map(
                user -> new UsernamePasswordAuthenticationToken(
                        userDetailsService.loadUserByUsername(user.getEmail()), token)
        );
    }
}
