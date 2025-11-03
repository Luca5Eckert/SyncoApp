package com.api.synco.module.course.domain.port;

import com.api.synco.module.course.domain.CourseEntity;

import java.util.Optional;

public interface CourseRepository {
    boolean existsByNameOrAcronym(String name, String acronym);

    void save(CourseEntity course);

    boolean existById(long id);

    void deleteById(long id);

    Optional<CourseEntity> findById(long id);
}
