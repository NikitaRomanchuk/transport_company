package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.db.models.User;

public interface JwtTokenService {
    String getToken(String login, String password);

    String createToken(User user);

}
