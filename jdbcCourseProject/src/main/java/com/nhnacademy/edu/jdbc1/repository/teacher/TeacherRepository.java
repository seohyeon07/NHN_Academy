package com.nhnacademy.edu.jdbc1.repository.teacher;



import com.nhnacademy.edu.jdbc1.domain.Teacher;

import java.util.List;

public interface TeacherRepository {
    Teacher findById(int id);

    List<Teacher> findAll();

}
