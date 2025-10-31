package com.api.blog.module.course.domain.use_cases;

import com.api.blog.module.course.application.dto.create.CreateCourseRequest;
import com.api.blog.module.course.domain.CourseEntity;
import com.api.blog.module.course.domain.exception.CourseNotUniqueException;
import com.api.blog.module.course.domain.exception.UserWithoutCreateCoursePermissionException;
import com.api.blog.module.course.domain.port.CourseRepository;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseUseCase {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CreateCourseUseCase(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public CourseEntity execute(CreateCourseRequest createCourseRequest, long idUser) {
        var user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));

        if (hasPermission(user)) throw new UserWithoutCreateCoursePermissionException();

        CourseEntity course = new CourseEntity(
                createCourseRequest.name(),
                createCourseRequest.acronym()
        );

        if(courseRepository.existByNameOrAcronym(course.getName(), course.getAcronym())){
            throw new CourseNotUniqueException();
        }

        courseRepository.save(course);

        return course;
    }

    private boolean hasPermission(UserEntity user) {
        return switch (user.getRole()) {
            case ADMIN -> true;
            default -> false;
        };
    }
}
