package com.api.blog.module.authentication.domain.use_case;

import com.api.blog.module.authentication.application.dto.reset_password.UserResetRequest;
import com.api.blog.module.authentication.domain.exception.password.PasswordNotMatchesException;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.exception.password.PasswordNotValidException;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.validator.PasswordValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserResetPasswordUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordValidatorImpl passwordValidator;

    @InjectMocks
    private UserResetPasswordUseCase userResetPasswordUseCase;

    private UserResetRequest userResetRequest;

    private long userId;

    private UserEntity user;

    @BeforeEach
    public void setup(){
        String passwordActual = "Senha#113";
        String newPassword = "Senha#113";
        userResetRequest = new UserResetRequest(passwordActual, newPassword);

        userId = 1L;
        user = new UserEntity(userId, null, null, passwordActual, null);
    }

    @Test
    @DisplayName("Should Continue When All Is Correct ")
    public void shouldContinueWhenAllIsCorrect(){

        // arrange
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(passwordValidator.isValid(any(String.class))).thenReturn(true);
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded");

        // act
        userResetPasswordUseCase.execute(userResetRequest, userId);

        // arrange
        var captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());

        var saved = captor.getValue();
        assertThat(saved).isNotNull();

    }

    @Test
    @DisplayName("Should Return Exception When Password Is Incorrect")
    public void shouldReturnExceptionWhenPasswordIsIncorrect(){

        // arrange
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        // act and assert
        assertThatThrownBy(() -> userResetPasswordUseCase.execute(userResetRequest, userId))
                .isExactlyInstanceOf(PasswordNotMatchesException.class);


    }


    @Test
    @DisplayName("Should Return Exception When Password Is Invalid")
    public void shouldReturnExceptionWhenPasswordIsInvalid(){

        // arrange
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(passwordValidator.isValid(any(String.class))).thenReturn(false);

        // act and assert
        assertThatThrownBy(() -> userResetPasswordUseCase.execute(userResetRequest, userId))
                .isExactlyInstanceOf(PasswordNotValidException.class);


    }

}