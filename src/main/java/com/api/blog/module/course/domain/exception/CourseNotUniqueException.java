package com.api.blog.module.course.domain.exception;

public class CourseNotUniqueException extends CourseException {

    public CourseNotUniqueException(String message) {
        super(message);
    }

    public CourseNotUniqueException() {
        super("The course is not unique.");
    }
}
