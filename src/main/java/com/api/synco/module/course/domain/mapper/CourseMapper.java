package com.api.synco.module.course.domain.mapper;

import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.domain.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CreateCourseResponse toCreateResponse(CourseEntity course) {
        return new CreateCourseResponse(
                course.getId(),
                course.getName(),
                course.getAcronym()
        );

    }
}
