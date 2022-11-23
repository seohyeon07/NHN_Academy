package com.nhnacademy.edu.jdbc1.repository.course;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.CourseRequest;

import java.util.List;

public interface CourseRepository {
    List<Course> findAll();

    Course findById(int id);

    void insert(CourseRequest courseRequest);

    void update(CourseRequest courseRequest);

    void delete(int id);
}
