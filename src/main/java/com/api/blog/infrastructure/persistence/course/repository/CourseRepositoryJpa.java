package com.api.blog.infrastructure.persistence.course.repository;

import com.api.blog.module.course.domain.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryJpa extends JpaRepository<CourseEntity, Long> {

    boolean existsByNameOrAcroym(String name, String acronym);

}
