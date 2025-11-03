package com.api.synco.module.user.domain.use_case;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.synco.module.user.domain.UserEntity;
import com.api.synco.module.user.domain.enumerator.RoleUser;
import com.api.synco.module.user.domain.exception.UserNotFoundDomainException;
import com.api.synco.module.user.domain.port.UserRepository;
import com.api.synco.module.user.domain.vo.Email;
import com.api.synco.module.user.domain.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserGetUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGetUseCase userGetUseCase;

    private long id;
    private UserEntity userEntity;

    @BeforeEach
    public void setup(){
        id = 1L;
        userEntity = new UserEntity(
            id,
            new Name("John Doe"),
            new Email("john@example.com"),
            "encodedPassword",
            RoleUser.USER
        );
    }

    @DisplayName("Should return user when found by ID")
    @Test
    public void shouldReturnUserWhenFound(){
        //arrange
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        //act
        var user = userGetUseCase.execute(id);

        //assert
        assertThat(user).isNotNull();
        assertThat(user.getName().value()).isEqualTo("John Doe");
        assertThat(user.getEmail().address()).isEqualTo("john@example.com");
        assertThat(user.getRole()).isEqualTo(RoleUser.USER);

        verify(userRepository).findById(id);
    }

    @DisplayName("Should throw UserNotFoundDomainException when user not found")
    @Test
    public void shouldThrowUserNotFoundExceptionWhenNotFound(){
        //arrange
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        //act and assert
        assertThatThrownBy(() -> userGetUseCase.execute(id))
                .isInstanceOf(UserNotFoundDomainException.class);

        verify(userRepository).findById(id);
    }
}
