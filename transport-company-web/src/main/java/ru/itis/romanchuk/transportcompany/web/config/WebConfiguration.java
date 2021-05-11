package ru.itis.romanchuk.transportcompany.web.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.romanchuk.transportcompany.api.config.ApplicationConfig;
import ru.itis.romanchuk.transportcompany.db.config.DbConfig;
import ru.itis.romanchuk.transportcompany.impl.config.ImplConfig;

@Configuration
@Import({ApplicationConfig.class, ImplConfig.class})
public class WebConfiguration {

}
