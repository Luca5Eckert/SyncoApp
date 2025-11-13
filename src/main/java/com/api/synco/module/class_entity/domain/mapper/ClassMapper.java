package com.api.synco.module.class_entity.domain.mapper;

import com.api.synco.module.class_entity.application.dto.create.CreateClassResponse;
import com.api.synco.module.class_entity.domain.ClassEntity;
import com.api.synco.module.class_entity.domain.ClassEntityId;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {

    public CreateClassResponse toCreateResponse(ClassEntity classEntity) {
        ClassEntityId classEntityId = classEntity.getId();

        return new CreateClassResponse(
                classEntityId.getCourseId(),
                classEntityId.getNumber(),
                classEntity.getTotalHours(),
                classEntity.getShift());
    }

}
