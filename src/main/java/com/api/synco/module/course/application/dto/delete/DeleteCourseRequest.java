package com.api.synco.module.course.application.dto.delete;

import com.api.synco.module.user.application.exception.UserIdInvalidException;

public record DeleteCourseRequest(long id) {

    public DeleteCourseRequest {
        if(id < 1){
            throw new IllegalArgumentException("User's id is invalid");
        }
    }

}
