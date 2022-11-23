package com.nhnacademy.edu.jdbc1.repository.subject;

import com.nhnacademy.edu.jdbc1.domain.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcSubjectRepository implements SubjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSubjectRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Subject> findAll() {
        return jdbcTemplate.query(
                "select id, name, created_at from JdbcSubjects",
                (resultSet, rowNum) ->
                        new Subject(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getTimestamp("created_at"))
        );

    }
}
