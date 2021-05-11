package ru.itis.romanchuk.transportcompany.web.security.filters;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.romanchuk.transportcompany.web.security.provider.JwtAuthenticationProvider;
import ru.itis.romanchuk.transportcompany.web.security.provider.OauthAuthenticationProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class OauthFilter extends GenericFilterBean {

    private final OauthAuthenticationProvider oauthAuthenticationProvider;

    public OauthFilter(OauthAuthenticationProvider oauthAuthenticationProvider) {
        this.oauthAuthenticationProvider = oauthAuthenticationProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        Cookie oauthCookie = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("oauth_token")) {
                    oauthCookie = cookie;
                    break;
                }
            }
            if (oauthCookie != null) {
                oauthAuthenticationProvider.authenticate(oauthCookie.getValue()).ifPresent(authentication -> {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
