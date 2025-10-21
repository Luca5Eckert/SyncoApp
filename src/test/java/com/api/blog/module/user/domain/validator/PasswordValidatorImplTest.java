package com.api.blog.module.user.domain.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.passay.PasswordValidator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorImplTest {

    private PasswordValidatorImpl passwordValidator;

    @BeforeEach
    public void setup(){
        passwordValidator = new PasswordValidatorImpl();
    }

    @Test
    public void shouldReturnTrueWhenPasswordIsStrong(){
        // -- arrange
        String password = "Liandra#113";

        // -- act
        var result = passwordValidator.isValid(password);

        // -- assert
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenPasswordDontHaveNumber(){
        // -- arrange
        String password = "Lucass#";


        // -- act
        var result = passwordValidator.isValid(password);

        // -- assert
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenPasswordDontHaveALetter(){
        // -- arrange
        String password = "#12345678";


        // -- act
        var result = passwordValidator.isValid(password);

        // -- assert
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenPasswordDontHaveEightCharacter(){
        // -- arrange
        String password = "Luca#1";


        // -- act
        var result = passwordValidator.isValid(password);

        // -- assert
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenPasswordDontHaveALowerCaseCharacter(){
        // -- arrange
        String password = "LUCAS#1";


        // -- act
        var result = passwordValidator.isValid(password);

        // -- assert
        assertThat(result).isFalse();
    }


}