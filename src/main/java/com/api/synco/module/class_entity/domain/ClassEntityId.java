package com.api.synco.module.class_entity.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ClassEntityId {

    private final long courseId;

    private final int number;

    public ClassEntityId() {
        this.courseId = -1;
        this.number = -1;
    }

    public ClassEntityId(long courseId, int number) {
        this.courseId = courseId;
        this.number = number;
    }

    public long getCourseId() {
        return courseId;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ClassEntityId that = (ClassEntityId) o;

        return courseId == that.courseId && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, number);
    }
}
