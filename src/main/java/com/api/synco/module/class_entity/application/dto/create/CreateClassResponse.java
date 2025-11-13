package com.api.synco.module.class_entity.application.dto.create;

import com.api.synco.module.class_entity.domain.enumerator.Shift;

public record CreateClassResponse(long courseId, int number, int totalHours, Shift shift) {
}
