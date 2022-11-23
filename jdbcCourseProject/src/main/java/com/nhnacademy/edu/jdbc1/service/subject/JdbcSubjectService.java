package com.nhnacademy.edu.jdbc1.service.subject;

import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.repository.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcSubjectService implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
