package com.api.synco.module.course.domain.exception.name;

import com.api.synco.module.course.domain.exception.CourseDomainException;

public class CourseNameBlankException extends CourseDomainException {
    public CourseNameBlankException() {
        super("Course's name can't be blank");
    }
}
