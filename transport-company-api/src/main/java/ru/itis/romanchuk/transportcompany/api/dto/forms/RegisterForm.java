package ru.itis.romanchuk.transportcompany.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.romanchuk.transportcompany.api.valid.EqualPasswords;
import ru.itis.romanchuk.transportcompany.api.valid.HasLetters;
import ru.itis.romanchuk.transportcompany.api.valid.HasNumber;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualPasswords(password = "password", passwordRepeat = "passwordRepeat")
public class RegisterForm {
    @Email
    private String email;

    @HasLetters
    @HasNumber
    private String password;

    private String passwordRepeat;
}
