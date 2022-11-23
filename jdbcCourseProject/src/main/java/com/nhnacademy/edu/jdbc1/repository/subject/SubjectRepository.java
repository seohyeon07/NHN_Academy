package com.nhnacademy.edu.jdbc1.repository.subject;

import com.nhnacademy.edu.jdbc1.domain.Subject;

import java.util.List;

public interface SubjectRepository {

    List<Subject> findAll();
}
