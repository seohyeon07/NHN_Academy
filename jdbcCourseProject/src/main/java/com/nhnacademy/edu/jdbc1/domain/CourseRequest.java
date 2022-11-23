package com.nhnacademy.edu.jdbc1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequest {
    private int id;
    private int teacherId;
    private int subjectId;

}
