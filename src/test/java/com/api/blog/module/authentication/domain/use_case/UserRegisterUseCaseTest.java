package com.api.blog.module.authentication.domain.use_case;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.blog.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.exception.email.EmailNotUniqueException;
import com.api.blog.module.user.domain.exception.password.PasswordNotValidException;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.validator.PasswordValidatorImpl;
import com.api.blog.module.user.domain.vo.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserRegisterUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordValidatorImpl passwordValidator;

    @InjectMocks
    private UserRegisterUseCase userRegisterUseCase;

    private UserRegisterRequest request;
    private String name;
    private String email;
    private String password;

    @BeforeEach
    public void setup(){
        name = "John Doe";
        email = "john@example.com";
        password = "Strong#Pass123";
        request = new UserRegisterRequest(name, email, password);
    }

    @DisplayName("Should register user successfully with valid data")
    @Test
    public void shouldRegisterUser(){
        //arrange
        when(passwordValidator.isValid(password)).thenReturn(true);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.existsByEmail(any(Email.class))).thenReturn(false);

        //act
        var user = userRegisterUseCase.execute(request);

        //assert - verify returned entity
        assertThat(user).isNotNull();
        assertThat(user.getName().value()).isEqualTo(name);
        assertThat(user.getEmail().address()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getRole()).isEqualTo(RoleUser.USER);

        //assert - verify repository.save was called
        var captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());
        var saved = captor.getValue();
        assertThat(saved.getName().value()).isEqualTo(name);
        assertThat(saved.getEmail().address()).isEqualTo(email);
        assertThat(saved.getRole()).isEqualTo(RoleUser.USER);
    }

    @DisplayName("Should throw EmailNotUniqueException when email already exists")
    @Test
    public void shouldThrowEmailNotUniqueException(){
        //arrange
        when(passwordValidator.isValid(password)).thenReturn(true);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.existsByEmail(new Email(email))).thenReturn(true);

        //act and assert
        assertThatThrownBy(() -> userRegisterUseCase.execute(request))
                .isInstanceOf(EmailNotUniqueException.class);

        verify(userRepository, never()).save(any());
    }

    @DisplayName("Should throw PasswordNotValidException when password is weak")
    @Test
    public void shouldThrowPasswordNotValidException(){
        //arrange
        when(passwordValidator.isValid(password)).thenReturn(false);

        //act and assert
        assertThatThrownBy(() -> userRegisterUseCase.execute(request))
                .isInstanceOf(PasswordNotValidException.class);

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).existsByEmail(any());
        verify(userRepository, never()).save(any());
    }
}
