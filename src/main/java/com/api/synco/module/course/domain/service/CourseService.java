package com.api.synco.module.course.domain.service;

import com.api.synco.module.course.application.dto.create.CreateCourseRequest;
import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.application.dto.delete.DeleteCourseRequest;
import com.api.synco.module.course.application.dto.update.UpdateCourseRequest;
import com.api.synco.module.course.application.dto.update.UpdateCourseResponse;
import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.mapper.CourseMapper;
import com.api.synco.module.course.domain.use_cases.CreateCourseUseCase;
import com.api.synco.module.course.domain.use_cases.DeleteCourseUseCase;
import com.api.synco.module.course.domain.use_cases.UpdateCourseUseCase;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    private final CreateCourseUseCase createCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;

    public CourseService(CourseMapper courseMapper, CreateCourseUseCase createCourseUseCase, DeleteCourseUseCase deleteCourseUseCase, UpdateCourseUseCase updateCourseUseCase) {
        this.courseMapper = courseMapper;
        this.createCourseUseCase = createCourseUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
        this.updateCourseUseCase = updateCourseUseCase;
    }

    /**
     * Update the user
     *
     * @param createCourseRequest The record with the data of request
     * @param idUser The id of the user authenticated
     *
     * @return A response with the data of course created
     */
    public CreateCourseResponse create(CreateCourseRequest createCourseRequest, long idUser) {
        var course = createCourseUseCase.execute(createCourseRequest, idUser);

        return courseMapper.toCreateResponse(course);
    }

    /**
     * Delete the user
     *
     * @param deleteCourseRequest The record with the data of request
     * @param idUser The id of the user authenticated
     */
    public void delete(DeleteCourseRequest deleteCourseRequest, long idUser){
        deleteCourseUseCase.execute(deleteCourseRequest, idUser);
    }

    /**
     * Update the user
     *
     * @param updateCourseRequest The record with the data of request
     * @param idCourse The id of the course who will be updated
     * @param idUser The id of the user authenticated
     *
     * @return A response with the data of course updated
     */
    public UpdateCourseResponse update(UpdateCourseRequest updateCourseRequest, long idCourse, long idUser){
        CourseEntity course = updateCourseUseCase.execute(updateCourseRequest, idCourse, idCourse);
        return courseMapper.toUpdateResponse(course);
    }


}
