package com.api.synco.module.course.application.dto.get;

public record GetCourseResponse(
        long id,
        String name,
        String acronym,
        String description
) {
}
