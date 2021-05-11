package ru.itis.romanchuk.transportcompany.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.romanchuk.transportcompany.db.models.OauthToken;

import java.util.Optional;

public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {
    Optional<OauthToken> getOauthTokenByValue(String value);
}
