package com.api.synco.module.class_entity.domain;

import com.api.synco.module.class_entity.domain.enumerator.Shift;
import com.api.synco.module.course.domain.CourseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private CourseEntity course;

    private Shift shift;

    public ClassEntity() {
    }

    public ClassEntity(String name, CourseEntity course, Shift shift) {
        this.name = name;
        this.course = course;
        this.shift = shift;
    }

    public ClassEntity(long id, String name, CourseEntity course, Shift shift) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.shift = shift;
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

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

}
