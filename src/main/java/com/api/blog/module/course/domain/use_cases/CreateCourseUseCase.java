package com.api.blog.module.course.domain.use_cases;

import com.api.blog.module.course.application.dto.create.CreateCourseRequest;
import com.api.blog.module.course.domain.CourseEntity;
import com.api.blog.module.course.domain.port.CourseRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseUseCase {

    private final CourseRepository courseRepository;

    public CreateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity execute(CreateCourseRequest createCourseRequest) {
        return null;
    }
}
