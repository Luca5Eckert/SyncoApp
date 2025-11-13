package com.api.synco.module.class_entity.application.dto.create;

import com.api.synco.module.class_entity.domain.enumerator.Shift;

public record CreateClassRequest(long courseId, int totalHours, Shift shift) {
}
