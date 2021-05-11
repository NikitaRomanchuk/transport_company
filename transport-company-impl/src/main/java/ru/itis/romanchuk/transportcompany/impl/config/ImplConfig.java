package ru.itis.romanchuk.transportcompany.impl.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.romanchuk.transportcompany.db.config.DbConfig;

@Configuration
@ComponentScan(basePackages = "ru.itis.romanchuk.transportcompany.impl")
@Import(DbConfig.class)
public class ImplConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }
}
