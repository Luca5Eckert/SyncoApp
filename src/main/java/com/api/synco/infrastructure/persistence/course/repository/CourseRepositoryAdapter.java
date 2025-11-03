package com.api.synco.infrastructure.persistence.course.repository;

import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.port.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseRepositoryJpa courseRepositoryJpa;

    public CourseRepositoryAdapter(CourseRepositoryJpa courseRepositoryJpa) {
        this.courseRepositoryJpa = courseRepositoryJpa;
    }

    @Override
    public boolean existsByNameOrAcronym(String name, String acronym) {
        return courseRepositoryJpa.existsByNameOrAcronym(name, acronym);
    }

    @Override
    public void save(CourseEntity course) {
        courseRepositoryJpa.save(course);
    }

    @Override
    public boolean existById(long id) {
        return courseRepositoryJpa.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        courseRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<CourseEntity> findById(long id) {
        return courseRepositoryJpa.findById(id);
    }
}
