package com.api.blog.module.user.domain.use_case;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
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
class UserCreateUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordValidatorImpl passwordValidator;

    @InjectMocks
    private UserCreateUseCase userCreateUseCase;
    
    private UserCreateRequest request;
    private String name;
    private String email;
    private String password;
    private RoleUser roleUser;

    @BeforeEach
    public void setup(){
        name = "lucas";
        email = "lucas@gmail.com";
        password = "Lucas#113";
        roleUser = RoleUser.USER;
        request = new UserCreateRequest(name, email, password, roleUser);
    }
    
    @DisplayName("This method execute should create user")
    @Test
    public void shouldCreateUser(){
        //arrange
        when(passwordEncoder.encode(password)).thenReturn("hash");
        when(passwordValidator.isValid(password)).thenReturn(true);
        when(userRepository.existsByEmail(any(Email.class))).thenReturn(false);

        //act
        var user = userCreateUseCase.execute(request);

        //assert -- returned entity has expected email and encoded passowod
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getEmail().address()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo("hash");
        assertThat(user.getRole()).isEqualTo(roleUser);

        //assert -= repository.save recive the same UserEntity
        var captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());
        var saved = captor.getValue();
        assertThat(saved.getName()).isEqualTo(user.getName());
        assertThat(saved.getEmail()).isEqualTo(user.getEmail());
        assertThat(saved.getRole()).isEqualTo(user.getRole());
    }

    @DisplayName("The method execute should throw a EmailNotUniqueException")
    @Test
    public void shouldThrowEmailNotUniqueException(){
        //arrange
        when(passwordEncoder.encode(password)).thenReturn("hash");
        when(passwordValidator.isValid(password)).thenReturn(true);
        when(userRepository.existsByEmail(new Email(email))).thenReturn(true);

        //act and assert
        assertThatThrownBy( () -> userCreateUseCase.execute(request))
                .isInstanceOf(EmailNotUniqueException.class);

        verify(userRepository, never()).save(any());

    }

    @DisplayName("Should Throw PasswordNotValidException")
    @Test
    public void shouldThrowPasswordNotValidException(){
        when(passwordValidator.isValid(password)).thenReturn(false);

        assertThatThrownBy( () -> userCreateUseCase.execute(request))
                .isExactlyInstanceOf(PasswordNotValidException.class);

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).existsByEmail(any());
        verify(userRepository, never()).save(any());
    }

}