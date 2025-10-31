package com.api.blog.infrastructure.persistence.course.repository;

import com.api.blog.module.course.domain.port.CourseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseRepositoryJpa courseRepositoryJpa;

    public CourseRepositoryAdapter(CourseRepositoryJpa courseRepositoryJpa) {
        this.courseRepositoryJpa = courseRepositoryJpa;
    }
}
