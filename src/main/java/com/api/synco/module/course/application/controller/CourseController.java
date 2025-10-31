package com.api.synco.module.course.application.controller;

import com.api.synco.core.UserAuthenticationService;
import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.module.course.application.dto.create.CreateCourseRequest;
import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.domain.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synco/app/courses")
public class CourseController {

    private final UserAuthenticationService authenticationService;

    private final CourseService courseService;


    public CourseController(UserAuthenticationService authenticationService, CourseService courseService) {
        this.authenticationService = authenticationService;
        this.courseService = courseService;
    }


    @Operation(
            summary = "Create the new course",
            description = "Creates a new course in the system. Requires authentication"
    )
    @ApiResponses ( value = {
            @ApiResponse (
                    responseCode = "201",
                    description = "The course is created success.",
                    content = @Content(schema = @Schema(implementation = CreateCourseResponse.class))
            ),
            @ApiResponse (
                    responseCode = "400",
                    description = "The course is not unique.",
                    content = @Content
            ),
            @ApiResponse (
                    responseCode = "400",
                    description = "The user don't have permission to create course.",
                    content = @Content
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
        }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomApiResponse<CreateCourseResponse>> create(@RequestBody @Valid CreateCourseRequest createCourseRequest){
        long idUser = authenticationService.getAuthenticatedUserId();

        var response = courseService.create(createCourseRequest, idUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomApiResponse.success(201, "The course is created success", response));
    }


}
