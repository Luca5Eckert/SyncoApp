package com.api.blog.module.course.domain;

import com.api.blog.module.class_entity.domain.ClassEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String acronym;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ClassEntity> classEntities;

    public CourseEntity() {
    }

    public CourseEntity(long id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

}
