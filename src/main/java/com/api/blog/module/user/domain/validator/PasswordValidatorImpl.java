package com.api.blog.module.user.domain.validator;

import org.passay.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PasswordValidatorImpl{

    private final PasswordValidator validator;

    public PasswordValidatorImpl() {
        List<Rule> rules = Arrays.asList(
                new LengthRule(8, 30),

                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                new CharacterRule(EnglishCharacterData.Digit, 1),

                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule()
        );

        this.validator = new PasswordValidator(rules);
    }

    public boolean isValid(String password){
        if (password == null) {
            return false;
        }
        return validator.validate(new PasswordData(password)).isValid();
    }

}
