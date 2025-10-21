package com.api.blog.module.user.domain.use_case;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.vo.Email;
import com.api.blog.module.user.domain.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserGetAllUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGetAllUseCase userGetAllUseCase;

    private List<UserEntity> userList;

    @BeforeEach
    public void setup(){
        UserEntity user1 = new UserEntity(
            1L,
            new Name("John Doe"),
            new Email("john@example.com"),
            "encodedPassword1",
            RoleUser.USER
        );

        UserEntity user2 = new UserEntity(
            2L,
            new Name("Jane Smith"),
            new Email("jane@example.com"),
            "encodedPassword2",
            RoleUser.ADMIN
        );

        userList = Arrays.asList(user1, user2);
    }

    @DisplayName("Should return all users when users exist")
    @Test
    public void shouldReturnAllUsers(){
        //arrange
        when(userRepository.findAll()).thenReturn(userList);

        //act
        var users = userGetAllUseCase.execute();

        //assert
        assertThat(users).isNotNull();
        assertThat(users).hasSize(2);
        assertThat(users.get(0).getName().value()).isEqualTo("John Doe");
        assertThat(users.get(1).getName().value()).isEqualTo("Jane Smith");

        verify(userRepository).findAll();
    }

    @DisplayName("Should return empty list when no users exist")
    @Test
    public void shouldReturnEmptyListWhenNoUsers(){
        //arrange
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        //act
        var users = userGetAllUseCase.execute();

        //assert
        assertThat(users).isNotNull();
        assertThat(users).isEmpty();

        verify(userRepository).findAll();
    }
}
