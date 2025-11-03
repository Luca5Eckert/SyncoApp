package com.api.synco.module.course.domain.exception.acronym;

import com.api.synco.module.course.domain.exception.CourseDomainException;

public class CourseAcronymBlankException extends CourseDomainException {

    public CourseAcronymBlankException() {
        super("Course's acronym can't be blank");
    }
}
