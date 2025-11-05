package com.api.synco.module.course.domain.use_cases;

import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.course.domain.filter.CourseFilter;
import com.api.synco.module.course.domain.filter.CourseSearchProvider;
import com.api.synco.module.course.domain.filter.PageCourse;
import com.api.synco.module.course.domain.port.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCourseUseCase {

    private final CourseRepository courseRepository;

    public GetAllCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Get all courses
     *
     * @param name Filter by name
     * @param acronym Filter by acronym
     * @param pageNumber Number of page
     * @param pageSize Quantity of courses per page
     * @return A page with courses entity
     */
    public Page<CourseEntity> execute(
            String name,
            String acronym,
            int pageNumber,
            int pageSize ){

        CourseFilter courseFilter = CourseFilter.builder()
                .setNameContains(name)
                .setAcronymContains(acronym)
                .build();

        var search = CourseSearchProvider.of(courseFilter);

        PageCourse pageCourse = new PageCourse(pageNumber, pageSize);

        return courseRepository.findAll(search, pageCourse);

    }

}
