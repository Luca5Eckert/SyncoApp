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

    /**
     * Create the course
     *
     * <p>
     *  The method performs in the following steps:
     *  <ol>
     *      <li>Find the user by {@code idUser}</li>
     *      <li>Valid the user permission</li>
     *      <li>Create the course entity</li>
     *      <li>Verify if the course is unique</li>
     *      <li>Save the user in Database</li>
     *  </ol>
     * </p>
     *
     * @param createCourseRequest Request containg the data for create a new course
     * @param idUser The id of User who wants create a course
     *
     * @throws UserNotFoundException If the user is not found
     * @throws UserWithoutCreateCoursePermissionException If the user don't have permission to create the course
     * @throws CourseNotUniqueException If the database alrealdy have a course with the same name or acronym
     *
     * @return The created and persisted {@link CourseEntity}, including its generated ID.
     * */

    public CourseEntity execute(CreateCourseRequest createCourseRequest, long idUser) {
        var user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));

        if (!hasPermission(user)) throw new UserWithoutCreateCoursePermissionException();

        CourseEntity course = new CourseEntity(
                createCourseRequest.name(),
                createCourseRequest.acronym()
        );

        if(courseRepository.existsByNameOrAcronym(course.getName(), course.getAcronym())){
            throw new CourseNotUniqueException();
        }

        courseRepository.save(course);

        return course;
    }

    /**
     * Verify if the user have permission to create a course
     *
     * @param user The user who will be verify
     * @return {@code True} if the user have permission, {@code False} if the user don't have permission
     */
    private boolean hasPermission(UserEntity user) {
        return switch (user.getRole()) {
            case ADMIN -> true;
            default -> false;
        };
    }
}
