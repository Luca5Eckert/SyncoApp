package com.api.synco.infrastructure.persistence.course.repository;

import com.api.synco.module.course.domain.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryJpa extends JpaRepository<CourseEntity, Long> {

    boolean existsByNameOrAcronym(String name, String acronym);

    Page<CourseEntity> findAll(Specification<CourseEntity> search, PageRequest pageRequest);
}
