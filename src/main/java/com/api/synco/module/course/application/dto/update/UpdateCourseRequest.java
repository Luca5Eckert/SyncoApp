package com.api.synco.module.course.application.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCourseRequest(
        @NotBlank(message = "Course's name can't be blank") @Size(max = 80, message = "Name's size can't be more than 80 caracteres") String name,
        @NotBlank(message = "Course's acronym can't be blank") @Size(max = 10, message = "Acronym's size can't be more than 10 caracteres") String acronym
) {
}
