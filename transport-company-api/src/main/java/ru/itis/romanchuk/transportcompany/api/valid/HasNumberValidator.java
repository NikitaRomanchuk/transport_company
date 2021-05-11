package ru.itis.romanchuk.transportcompany.api.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasNumberValidator implements ConstraintValidator<HasNumber, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(".*[0-9].*");
    }
}
