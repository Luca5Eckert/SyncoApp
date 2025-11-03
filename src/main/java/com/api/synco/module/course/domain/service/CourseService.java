package com.api.synco.module.course.domain.service;

import com.api.synco.module.course.application.dto.create.CreateCourseRequest;
import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.application.dto.delete.DeleteCourseRequest;
import com.api.synco.module.course.domain.mapper.CourseMapper;
import com.api.synco.module.course.domain.use_cases.CreateCourseUseCase;
import com.api.synco.module.course.domain.use_cases.DeleteCourseUseCase;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    private final CreateCourseUseCase createCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;

    public CourseService(CourseMapper courseMapper, CreateCourseUseCase createCourseUseCase, DeleteCourseUseCase deleteCourseUseCase) {
        this.courseMapper = courseMapper;
        this.createCourseUseCase = createCourseUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
    }

    public CreateCourseResponse create(CreateCourseRequest createCourseRequest, long idUser) {
        var course = createCourseUseCase.execute(createCourseRequest, idUser);

        return courseMapper.toCreateResponse(course);
    }

    public void delete(DeleteCourseRequest deleteCourseRequest, long idUser){
        deleteCourseUseCase.execute(deleteCourseRequest, idUser);
    }

}
