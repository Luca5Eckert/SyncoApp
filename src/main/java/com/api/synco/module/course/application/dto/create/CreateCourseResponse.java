package com.api.synco.module.course.application.dto.create;

public record CreateCourseResponse(
        long id,
        String name,
        String acronym,
        String description
) {
}
