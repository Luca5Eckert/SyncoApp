package com.api.synco.module.course.domain.exception;

public class CourseNotFoundException extends CourseDomainException {

    public CourseNotFoundException(long id) {
        super("Course not found by id: " + id);
    }


}
