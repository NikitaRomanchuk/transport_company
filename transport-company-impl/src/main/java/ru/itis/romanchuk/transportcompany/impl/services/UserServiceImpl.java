package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.dto.UserDto;
import ru.itis.romanchuk.transportcompany.api.exceptions.EmailOccupiedException;
import ru.itis.romanchuk.transportcompany.api.services.UserService;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.api.dto.forms.RegisterForm;
import ru.itis.romanchuk.transportcompany.db.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterForm form) throws EmailOccupiedException {
        Optional<User> user = repository.findByEmail(form.getEmail());
        if(user.isPresent()){
            throw new EmailOccupiedException(form.getEmail() + " is occupied");
        }
        User newUser = User.builder()
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .build();
        repository.save(newUser);
    }

    @Override
    public List<UserDto> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size );
        List<User> usersPage = repository.findAll(pageable).getContent();
        return UserDto.from(usersPage);
    }

    @Override
    public User getUserById(Long id) {
        return repository.getOne(id);
    }
}
