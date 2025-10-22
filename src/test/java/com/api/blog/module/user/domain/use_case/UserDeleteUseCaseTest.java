package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.exception.permission.UserWithoutDeletePermissionException;
import com.api.blog.module.user.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDeleteUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDeleteUseCase userDeleteUseCase;

    private long id;
    private UserEntity userEntity;

    private UserDeleteRequest userDeleteRequest;

    private long idUserAuthentication;
    private UserEntity userAuthenticated;

    @BeforeEach
    void setup() {
        id = 1L;
        userEntity = new UserEntity();

        userDeleteRequest = new UserDeleteRequest(id);

        idUserAuthentication = 2L;
        userAuthenticated = new UserEntity(null, null, null, RoleUser.ADMIN);
    }

    @Test
    public void shouldDeleteUserWhenExists(){
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.findById(idUserAuthentication)).thenReturn(Optional.of(userAuthenticated));

        userDeleteUseCase.execute(userDeleteRequest, idUserAuthentication);

        verify(userRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenUserNotExist() {
        when(userRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> userDeleteUseCase.execute(userDeleteRequest, idUserAuthentication))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldThrowWhenUserDontHavePermission() {
        var userWithoutPermission = new UserEntity(null, null, null, RoleUser.USER);

        //arrange
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.findById(idUserAuthentication)).thenReturn(Optional.of(userWithoutPermission));

        //act and assert
        assertThatThrownBy(() -> userDeleteUseCase.execute(userDeleteRequest, idUserAuthentication))
                .isExactlyInstanceOf(UserWithoutDeletePermissionException.class);

    }

}