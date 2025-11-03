package com.api.synco.module.course.domain.use_cases;

import com.api.synco.module.course.application.dto.update.UpdateCourseRequest;
import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.exception.CourseNotExistException;
import com.api.synco.module.course.domain.exception.UserWithoutDeleteCoursePermissionException;
import com.api.synco.module.course.domain.port.CourseRepository;
import com.api.synco.module.user.domain.exception.UserNotFoundDomainException;
import com.api.synco.module.user.domain.port.UserRepository;

public class UpdateCourseUseCase {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public UpdateCourseUseCase(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public CourseEntity execute(UpdateCourseRequest updateCourseRequest, long idCourse, long idUserAuthenticated){
        var user = userRepository.findById(idUserAuthenticated).orElseThrow(() -> new UserNotFoundDomainException(idUserAuthenticated));

        if (!CourseEntity.havePermissionToModify(user.getRole())) throw new UserWithoutDeleteCoursePermissionException();

        CourseEntity course = courseRepository.findById(idCourse).orElseThrow( () -> new CourseNotExistException(idCourse));

        course.setName(updateCourseRequest.name());
        course.setAcronym(updateCourseRequest.acronym());

        courseRepository.save(course);

        return course;
    }


}
