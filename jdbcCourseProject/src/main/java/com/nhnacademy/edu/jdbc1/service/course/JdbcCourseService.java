package com.nhnacademy.edu.jdbc1.service.course;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.CourseRequest;
import com.nhnacademy.edu.jdbc1.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JdbcCourseService implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Course getCourseById(int id){ return courseRepository.findById(id);}


    @Override
    @Transactional
    public void updateCourse(CourseRequest courseRequest) {
        courseRepository.update(courseRequest);
    }

    @Override
    @Transactional
    public void createCourse(CourseRequest courseRequest) {
        courseRepository.insert(courseRequest);
    }

    @Override
    @Transactional
    public void deleteCourses(int id) {
        courseRepository.delete(id);
    }
}
