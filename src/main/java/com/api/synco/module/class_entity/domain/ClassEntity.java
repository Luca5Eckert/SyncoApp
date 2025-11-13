package com.api.synco.module.class_entity.domain;

import com.api.synco.module.class_entity.domain.enumerator.Shift;
import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.user.domain.enumerator.RoleUser;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ClassEntity {

    @EmbeddedId
    private ClassEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private int totalHours;

    private Shift shift;

    public ClassEntity() {
    }

    public ClassEntity(CourseEntity course, int totalHours, Shift shift) {
        this.course = course;
        this.totalHours = totalHours;
        this.shift = shift;
    }


    public ClassEntity(ClassEntityId id, CourseEntity course, int totalHours, Shift shift) {
        this.id = id;
        this.course = course;
        this.totalHours = totalHours;
        this.shift = shift;
    }

    public static boolean havePermissionToModify(RoleUser role) {
        return switch (role){
            case ADMIN -> true;
            case USER -> false;
        };
    }

    public ClassEntityId getId() {
        return id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }


    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return totalHours == that.totalHours && Objects.equals(id, that.id) && Objects.equals(course, that.course) && shift == that.shift;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, totalHours, shift);
    }

}
