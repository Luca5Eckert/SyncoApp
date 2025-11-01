package com.api.synco.module.course.domain.port;

import com.api.synco.module.course.domain.CourseEntity;

public interface CourseRepository {
    boolean existsByNameOrAcronym(String name, String acronym);

    void save(CourseEntity course);

    boolean existById(long id);

    void deleteById(long id);
}
