package com.api.synco.module.course.domain;

import com.api.synco.module.class_entity.domain.ClassEntity;
import com.api.synco.module.course.domain.exception.acronym.CourseAcronymBlankException;
import com.api.synco.module.course.domain.exception.name.CourseNameBlankException;
import com.api.synco.module.user.domain.enumerator.RoleUser;
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

    public CourseEntity(String name, String acronym) {
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
        if(name == null || name.isBlank()){
            throw new CourseNameBlankException();
        }
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        if(acronym == null || acronym.isBlank()){
            throw new CourseAcronymBlankException();
        }
        this.acronym = acronym;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    public static boolean havePermissionToModify(RoleUser roleUser){
        return switch (roleUser){
            case ADMIN -> true;
            default -> false;
        };
    }


}
