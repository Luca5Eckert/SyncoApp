package com.api.synco.module.course.domain.exception;

public class CourseNotUniqueException extends CourseDomainException {

    public CourseNotUniqueException(String message) {
        super(message);
    }

    public CourseNotUniqueException() {
        super("The course is not unique.");
    }
}
