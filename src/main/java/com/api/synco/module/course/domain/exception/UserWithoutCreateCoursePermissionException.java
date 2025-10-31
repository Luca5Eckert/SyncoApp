package com.api.synco.module.course.domain.exception;

public class UserWithoutCreateCoursePermissionException extends CourseException {

    public UserWithoutCreateCoursePermissionException(String message) {
        super(message);
    }

    public UserWithoutCreateCoursePermissionException() {
        super("The user don't have permission to create course");
    }
}
