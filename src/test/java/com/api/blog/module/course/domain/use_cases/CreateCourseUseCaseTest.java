package com.api.blog.module.course.domain.use_cases;

import com.api.blog.module.course.application.dto.create.CreateCourseRequest;
import com.api.blog.module.course.domain.CourseEntity;
import com.api.blog.module.course.domain.exception.CourseNotUniqueException;
import com.api.blog.module.course.domain.exception.UserWithoutCreateCoursePermissionException;
import com.api.blog.module.course.domain.port.CourseRepository;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCourseUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CreateCourseUseCase createCourseUseCase;

    private CreateCourseRequest createCourseRequest;

    private long id;

    private UserEntity user;

    @BeforeEach
    public void setup(){
        createCourseRequest = new CreateCourseRequest("Nome", "N");

        user = new UserEntity(null, null, null, RoleUser.ADMIN);
    }

    @Test
    public void shouldCreateTheCourseWhenEverythingIsRight(){
        // arrange
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(courseRepository.existsByNameOrAcronym(any(String.class), any(String.class))).thenReturn(false);

        // act
        var response = createCourseUseCase.execute(createCourseRequest, id);

        // assert
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(createCourseRequest.name());
        assertThat(response.getAcronym()).isEqualTo(createCourseRequest.acronym());

        var captor = ArgumentCaptor.forClass(CourseEntity.class);
        verify(courseRepository).save(captor.capture());
        var saved = captor.getValue();

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo(createCourseRequest.name());
        assertThat(saved.getAcronym()).isEqualTo(createCourseRequest.acronym());

    }

    @Test
    public void shouldThrowExceptionWhenTheUserDontHavePermission(){

        // arrange
        user.setRole(RoleUser.USER);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        // act and assert
        assertThatThrownBy( () -> createCourseUseCase.execute(createCourseRequest, id))
                .isInstanceOf(UserWithoutCreateCoursePermissionException.class);


    }

    @Test
    public void shouldThrowExceptionWhenTheUserDontExist(){

        // arrange
        user.setRole(RoleUser.USER);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // act and assert
        assertThatThrownBy( () -> createCourseUseCase.execute(createCourseRequest, id))
                .isInstanceOf(UserNotFoundException.class);


    }

    @Test
    public void shouldThrowExceptionWhenTheCourseIsNotUnique(){

        // arrange
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(courseRepository.existsByNameOrAcronym(any(String.class), any(String.class))).thenReturn(true);

        // act and assert
        assertThatThrownBy( () -> createCourseUseCase.execute(createCourseRequest, id))
                .isInstanceOf(CourseNotUniqueException.class);


    }


}