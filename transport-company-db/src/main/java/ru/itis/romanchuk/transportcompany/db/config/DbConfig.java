package ru.itis.romanchuk.transportcompany.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "ru.itis.romanchuk.transportcompany.db.repositories")
@EntityScan(basePackages = "ru.itis.romanchuk.transportcompany.db.models")
public class DbConfig {
}
