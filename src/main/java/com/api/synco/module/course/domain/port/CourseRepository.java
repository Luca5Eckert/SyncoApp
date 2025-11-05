package com.api.synco.module.course.domain.port;

import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.filter.PageCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface CourseRepository {
    boolean existsByNameOrAcronym(String name, String acronym);

    void save(CourseEntity course);

    boolean existById(long id);

    void deleteById(long id);

    Optional<CourseEntity> findById(long id);

    Page<CourseEntity> findAll(Specification<CourseEntity> search, PageCourse pageCourse);
}
