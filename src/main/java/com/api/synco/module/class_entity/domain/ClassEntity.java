package com.api.synco.module.class_entity.domain;

import com.api.synco.module.class_entity.domain.enumerator.Shift;
import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.user.domain.enumerator.RoleUser;
import jakarta.persistence.*;

@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;

    private int totalHours;

    private Shift shift;

    public ClassEntity() {
    }

    public ClassEntity(int number, CourseEntity course, int totalHours, Shift shift) {
        this.number = number;
        this.course = course;
        this.totalHours = totalHours;
        this.shift = shift;
    }

    public ClassEntity(CourseEntity course, int totalHours, Shift shift) {
        this.course = course;
        this.totalHours = totalHours;
        this.shift = shift;
    }


    public ClassEntity(long id, int number, CourseEntity course, int totalHours, Shift shift) {
        this.id = id;
        this.number = number;
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

    public long getId() {
        return id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

}
