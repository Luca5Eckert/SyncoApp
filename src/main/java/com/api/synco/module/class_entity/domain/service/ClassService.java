package com.api.synco.module.class_entity.domain.service;

import com.api.synco.module.class_entity.application.dto.create.CreateClassRequest;
import com.api.synco.module.class_entity.application.dto.create.CreateClassResponse;
import com.api.synco.module.class_entity.domain.ClassEntity;
import com.api.synco.module.class_entity.domain.mapper.ClassMapper;
import com.api.synco.module.class_entity.domain.port.ClassRepository;
import com.api.synco.module.class_entity.domain.use_case.CreateClassUseCase;
import com.api.synco.module.course.domain.use_cases.CreateCourseUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private final ClassMapper classMapper;

    private final CreateClassUseCase createClassUseCase;

    public ClassService(ClassMapper classMapper, CreateClassUseCase createClassUseCase) {
        this.classMapper = classMapper;
        this.createClassUseCase = createClassUseCase;
    }

    public CreateClassResponse create(CreateClassRequest createClassRequest, long idUser) {
        ClassEntity classEntity = createClassUseCase.execute(createClassRequest, idUser);

        return classMapper.toCreateResponse(classEntity);
    }

}
