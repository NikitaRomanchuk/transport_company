package ru.itis.romanchuk.transportcompany.impl.services;

import okhttp3.*;
import okhttp3.Request.Builder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.services.OauthTokenService;
import ru.itis.romanchuk.transportcompany.db.models.OauthToken;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.db.repositories.OauthTokenRepository;
import ru.itis.romanchuk.transportcompany.db.repositories.UserRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {

    private OauthTokenRepository oauthTokenRepository;

    private UserRepository userRepository;

    private OkHttpClient client;

    public static final String GITHUB_URL = "https://github.com/login/oauth/access_token";

    @Value("${oauth.github.client-id}")
    private String clientId;

    @Value("${oauth.github.client-secret}")
    private String clientSecret;

    public OauthTokenServiceImpl(OauthTokenRepository oauthTokenRepository, OkHttpClient client, UserRepository userRepository) {
        this.oauthTokenRepository = oauthTokenRepository;
        this.client = client;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        return oauthTokenRepository.getOauthTokenByValue(token).map(OauthToken::getPrincipal);
    }

    @Override
    public OauthToken getToken(String temporaryToken) {
        RequestBody body = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("code", temporaryToken)
                .build();
        Request request = new Request.Builder().url(GITHUB_URL).post(body).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String params = Objects.requireNonNull(response.body()).string();
            String token = params.substring(13,53);
            System.out.println(token);
            System.out.println(params);
            request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .addHeader("Authorization", "token " + token)
                    .build();
            response = client.newCall(request).execute();
            JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).string());
            String email = json.getString("email");
            Optional<User> optionalUser = userRepository.findByEmail(email);
            User user = null;
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
            else {
                user = User.builder().email(email).role(User.Role.USER).hashPassword("").build();
                userRepository.save(user);
            }
            OauthToken oauthToken = OauthToken.builder().value(token).principal(user).build();
            oauthTokenRepository.save(oauthToken);
            System.out.println(json.getString("email"));
            return oauthToken;
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
