package com.api.synco.module.class_entity.application.controller;


import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.module.class_entity.application.dto.create.CreateClassRequest;
import com.api.synco.module.class_entity.application.dto.create.CreateClassResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/classes")
@RestController
public class ClassController {

    public ResponseEntity<CustomApiResponse<CreateClassResponse>> create(@Valid CreateClassRequest createClassRequest){
        return null;
    }

}
