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

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ClassEntity> classEntities;

    public CourseEntity() {
    }

    public CourseEntity(long id, String name, String acronym, String description) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.description = description;
    }

    public CourseEntity(String name, String acronym, String description) {
        this.name = name;
        this.acronym = acronym;
        this.description = description;
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

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
