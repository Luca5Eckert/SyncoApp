package com.api.synco.module.course.domain.exception;

public class UserWithoutDeleteCoursePermissionException extends CourseDomainException {
    public UserWithoutDeleteCoursePermissionException(String message) {
        super(message);
    }

    public UserWithoutDeleteCoursePermissionException() {
        super("The user don't have permission to create course");
    }

}
