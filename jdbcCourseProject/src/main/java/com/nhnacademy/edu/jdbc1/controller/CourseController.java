package com.nhnacademy.edu.jdbc1.controller;

import com.nhnacademy.edu.jdbc1.domain.Course;
import com.nhnacademy.edu.jdbc1.domain.CourseRequest;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import com.nhnacademy.edu.jdbc1.service.course.CourseService;
import com.nhnacademy.edu.jdbc1.service.subject.SubjectService;
import com.nhnacademy.edu.jdbc1.service.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseCreationService;

    private final TeacherService teacherService;

    private final SubjectService subjectService;


    @GetMapping
    public String viewCourses(Model model) {
        List<Course> courseList = courseCreationService.getAllCourses();
        model.addAttribute("courseList", courseList);

        return "coursesView";
    }

    @GetMapping("/course/{id}")
    public String DetailView(@PathVariable Integer id, Model model) {
        Course course = courseCreationService.getCourseById(id);

        model.addAttribute("course", course);

        return "courseDetailView";
    }


    // 등록 폼
    @GetMapping("/register")
    public String createCourse(Model model) {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Subject> subjectList = subjectService.getAllSubjects();

        model.addAttribute("course", new CourseRequest());
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("subjectList", subjectList);


        return "courseRegisterForm";
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable Integer id, Model model) {
        Course course = courseCreationService.getCourseById(id);
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Subject> subjectList = subjectService.getAllSubjects();

        model.addAttribute("course", course);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("subjectList", subjectList);

        return "courseRegisterForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        courseCreationService.deleteCourses(id);
        return "redirect:/courses";
    }


    @PostMapping("/register")
    public String registerCourse(@ModelAttribute CourseRequest courseRequest) {
        courseCreationService.createCourse(courseRequest);
        return "redirect:/courses";
    }

    @PostMapping("update/{id}")
    public String postUpdateCourse(@ModelAttribute CourseRequest courseRequest) {
        courseCreationService.updateCourse(courseRequest);
        return "redirect:/courses";
    }


}
