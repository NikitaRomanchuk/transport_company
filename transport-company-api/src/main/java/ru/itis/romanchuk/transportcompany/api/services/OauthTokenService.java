package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.db.models.OauthToken;
import ru.itis.romanchuk.transportcompany.db.models.User;

import java.util.Optional;

public interface OauthTokenService {
    Optional<User> getUserByToken(String token);

    OauthToken getToken(String temporaryToken);
}
