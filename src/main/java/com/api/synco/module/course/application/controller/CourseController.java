package com.api.synco.module.course.application.controller;

import com.api.synco.core.UserAuthenticationService;
import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.module.course.application.dto.create.CreateCourseRequest;
import com.api.synco.module.course.application.dto.create.CreateCourseResponse;
import com.api.synco.module.course.application.dto.delete.DeleteCourseRequest;
import com.api.synco.module.course.domain.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Endpoints for the course management")
@SecurityRequirement(name = "bearer-jwt")
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


    @Operation(
            summary = "Delete the course",
            description = "Delete the course in the System. Requires authentication"
    )
    @ApiResponses ( value = {
            @ApiResponse (
                    responseCode = "202",
                    description = "The course is deleted success.",
                    content = @Content(schema = @Schema(implementation = CreateCourseResponse.class))
            ),
            @ApiResponse (
                    responseCode = "400",
                    description = "The user don't have permission to delete course.",
                    content = @Content
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomApiResponse<Void>> delele(@PathVariable long idUser){
        DeleteCourseRequest request = new DeleteCourseRequest(idUser);

        long idUserAuthenticated = authenticationService.getAuthenticatedUserId();

        courseService.delete(request, idUserAuthenticated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(CustomApiResponse.success(202, "The course is deleted success"));
    }


}
