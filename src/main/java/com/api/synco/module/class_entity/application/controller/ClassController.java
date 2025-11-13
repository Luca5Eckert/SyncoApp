package com.api.synco.module.class_entity.application.controller;


import com.api.synco.core.UserAuthenticationService;
import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.module.class_entity.application.dto.create.CreateClassRequest;
import com.api.synco.module.class_entity.application.dto.create.CreateClassResponse;
import com.api.synco.module.class_entity.domain.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/classes")
@RestController
public class ClassController {

    private final ClassService classService;

    private final UserAuthenticationService userAuthenticationService;

    public ClassController(ClassService classService, UserAuthenticationService userAuthenticationService) {
        this.classService = classService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<CreateClassResponse>> create(@Valid @RequestBody CreateClassRequest createClassRequest){
        long idUser = userAuthenticationService.getAuthenticatedUserId();

        var createResponse =  classService.create(createClassRequest, idUser);

        HttpStatus status = HttpStatus.CREATED;

        return ResponseEntity.status(status).body(CustomApiResponse.success(status.value(), "Class created with success", createResponse));
    }

}
