package com.api.synco.module.course.domain.exception;

public class CourseNotExistException extends RuntimeException {

    public CourseNotExistException(long id) {
        super("The course don't exist by " + id);
    }


}
