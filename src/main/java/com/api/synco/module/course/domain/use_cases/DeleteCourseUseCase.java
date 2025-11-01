package com.api.synco.module.course.domain.use_cases;

import com.api.synco.module.course.application.dto.delete.DeleteCourseRequest;
import com.api.synco.module.course.domain.exception.CourseNotExistException;
import com.api.synco.module.course.domain.exception.UserWithoutDeleteCoursePermissionException;
import com.api.synco.module.course.domain.port.CourseRepository;
import com.api.synco.module.user.domain.UserEntity;
import com.api.synco.module.user.domain.exception.UserNotFoundException;
import com.api.synco.module.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteCourseUseCase {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public DeleteCourseUseCase(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Delete the course
     *
     * <p>
     *     The method performs in the following steps:
     *     <ol>
     *          <li>Find the user by {@code idUser}</li>
     *          <li>Valid the user permission</li>
     *          <li>Verify if the course exist</li>
     *          <li></li>
     *     </ol>
     * </p>
     *
     * @param deleteCourseRequest
     * @param idUser
     */
    public void execute(DeleteCourseRequest deleteCourseRequest, long idUser){
        var user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));

        if (!hasPermission(user)) throw new UserWithoutDeleteCoursePermissionException();

        if(courseRepository.existById(deleteCourseRequest.id())) throw new CourseNotExistException(deleteCourseRequest.id());

        courseRepository.deleteById(deleteCourseRequest.id());

    }

    private boolean hasPermission(UserEntity user) {
        return switch (user.getRole()){
            case ADMIN -> true;
            default -> false;
        };
    }

}
