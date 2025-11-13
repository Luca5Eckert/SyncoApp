package com.api.synco.module.class_entity.domain.service;

import com.api.synco.module.class_entity.domain.port.ClassRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    public final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


}
