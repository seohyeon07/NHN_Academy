package com.nhnacademy.edu.jdbc1.service.course;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(int id);

    void updateCourse(CourseRequest courseRequest);

    void createCourse(CourseRequest courseRequest);

    void deleteCourses(int id);

}
