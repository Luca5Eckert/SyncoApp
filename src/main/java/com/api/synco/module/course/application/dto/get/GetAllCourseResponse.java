package com.api.synco.module.course.application.dto.get;

public record GetAllCourseResponse(
        long id,
        String name,
        String acronym,
        String description
) {
}
