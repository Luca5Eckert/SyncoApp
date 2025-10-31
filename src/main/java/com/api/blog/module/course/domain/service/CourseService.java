package com.api.blog.module.course.domain.service;

import com.api.blog.module.course.application.dto.create.CreateCourseRequest;
import com.api.blog.module.course.application.dto.create.CreateCourseResponse;
import com.api.blog.module.course.domain.mapper.CourseMapper;
import com.api.blog.module.course.domain.use_cases.CreateCourseUseCase;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    private final CreateCourseUseCase createCourseUseCase;

    public CourseService(CourseMapper courseMapper, CreateCourseUseCase createCourseUseCase) {
        this.courseMapper = courseMapper;
        this.createCourseUseCase = createCourseUseCase;
    }

    public CreateCourseResponse create(CreateCourseRequest createCourseRequest) {
        var course = createCourseUseCase.execute(createCourseRequest);

        return courseMapper.toCreateResponse(course);
    }

}
