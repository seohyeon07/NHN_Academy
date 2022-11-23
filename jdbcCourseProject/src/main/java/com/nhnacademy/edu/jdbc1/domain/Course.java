package com.nhnacademy.edu.jdbc1.domain;

import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Course {
    private final int id;
    private final Teacher teacher;
    private final Subject subject;
    private final Date createdAt;

}
