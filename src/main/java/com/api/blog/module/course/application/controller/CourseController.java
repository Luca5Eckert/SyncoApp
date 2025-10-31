package com.api.blog.module.course.application.controller;

import com.api.blog.infrastructure.api.CustomApiResponse;
import com.api.blog.module.course.application.dto.create.CreateCourseRequest;
import com.api.blog.module.course.application.dto.create.CreateCourseResponse;
import com.api.blog.module.course.domain.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<CreateCourseResponse>> create(@Valid CreateCourseRequest createCourseRequest){
        var response = courseService.create(createCourseRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomApiResponse.success(201, "The course is created success", response));
    }

}
