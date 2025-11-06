package com.api.synco.module.course.domain.service;

import com.api.synco.module.course.application.dto.create.CreateCourseRequest;
import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.application.dto.delete.DeleteCourseRequest;
import com.api.synco.module.course.application.dto.get.GetAllCourseResponse;
import com.api.synco.module.course.application.dto.get.GetCourseResponse;
import com.api.synco.module.course.application.dto.update.UpdateCourseRequest;
import com.api.synco.module.course.application.dto.update.UpdateCourseResponse;
import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.mapper.CourseMapper;
import com.api.synco.module.course.domain.use_cases.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    private final CreateCourseUseCase createCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final GetAllCourseUseCase getAllCourseUseCase;
    private final GetCourseUseCase getCourseUseCase;

    public CourseService(CourseMapper courseMapper, CreateCourseUseCase createCourseUseCase, DeleteCourseUseCase deleteCourseUseCase, UpdateCourseUseCase updateCourseUseCase, GetAllCourseUseCase getAllCourseUseCase, GetCourseUseCase getCourseUseCase) {
        this.courseMapper = courseMapper;
        this.createCourseUseCase = createCourseUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
        this.updateCourseUseCase = updateCourseUseCase;
        this.getAllCourseUseCase = getAllCourseUseCase;
        this.getCourseUseCase = getCourseUseCase;
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

    /**
     * Get the user
     *
     * @param id The id of the course
     * @return Response with data of course
     */
    public GetCourseResponse getCourse(long id){
        CourseEntity course = getCourseUseCase.execute(id);

        return courseMapper.toGetResponse(course);
    }


    /**
     * Get all users
     *
     * @param name The name who will filter courses
     * @param acronym The acronym who will filter courses
     * @param pageNumber The number of page of courses
     * @param pageSize The number of course per page
     *
     * @return A List with GetCourseResponse
     */
    public List<GetAllCourseResponse> getAll(
            String name,
            String acronym,
            int pageNumber,
            int pageSize
    ){

        var listUsers = getAllCourseUseCase.execute(
                name,
                acronym,
                pageNumber,
                pageSize
        );

        return listUsers.stream()
                .map(courseMapper::toGetAllResponse)
                .toList();

    }


}
