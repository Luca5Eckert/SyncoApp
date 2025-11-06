package com.api.synco.module.course.domain.mapper;

import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.application.dto.get.GetCourseResponse;
import com.api.synco.module.course.application.dto.update.UpdateCourseResponse;
import com.api.synco.module.course.domain.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CreateCourseResponse toCreateResponse(CourseEntity course) {
        return new CreateCourseResponse(
                course.getId(),
                course.getName(),
                course.getAcronym(),
                course.getDescription()
        );

    }

    public UpdateCourseResponse toUpdateResponse(CourseEntity course) {
        return new UpdateCourseResponse(
                course.getId(),
                course.getName(),
                course.getAcronym(),
                course.getDescription()
        );
    }

    public GetCourseResponse toGetResponse(CourseEntity course) {
        return new GetCourseResponse(
                course.getId(),
                course.getName(),
                course.getAcronym(),
                course.getDescription()
        );
    }

}
