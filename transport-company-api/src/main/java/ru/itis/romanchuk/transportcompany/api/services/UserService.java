package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.api.dto.UserDto;
import ru.itis.romanchuk.transportcompany.api.exceptions.EmailOccupiedException;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.api.dto.forms.RegisterForm;

import java.util.List;

public interface UserService {
    void register(RegisterForm form) throws EmailOccupiedException;

    List<UserDto> getUsers(int page, int size);

    User getUserById(Long id);
}
