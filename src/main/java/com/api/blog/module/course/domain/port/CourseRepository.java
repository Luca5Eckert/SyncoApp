package com.api.blog.module.course.domain.port;

import com.api.blog.module.course.domain.CourseEntity;

public interface CourseRepository {
    boolean existsByNameOrAcronym(String name, String acronym);

    void save(CourseEntity course);
}
