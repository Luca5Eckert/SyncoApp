package com.api.blog.module.course.domain.mapper;

import com.api.blog.module.course.application.dto.create.CreateCourseResponse;
import com.api.blog.module.course.domain.CourseEntity;
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
