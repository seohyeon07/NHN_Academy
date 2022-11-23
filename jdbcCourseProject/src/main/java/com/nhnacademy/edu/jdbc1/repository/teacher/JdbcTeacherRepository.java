package com.nhnacademy.edu.jdbc1.repository.teacher;


import com.nhnacademy.edu.jdbc1.domain.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTeacherRepository implements TeacherRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeacherRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Teacher findById(int id) {
        return jdbcTemplate.queryForObject(
                "select id, name, created_at from JdbcTeachers where id =?",
                (resultSet, rowNum) ->
                        new Teacher(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getTimestamp("created_at")),id);
    }

    @Override
    public List<Teacher> findAll() {
        return jdbcTemplate.query(
                "select id, name, created_at from JdbcTeachers",
                (resultSet, rowNum) ->
                        new Teacher(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getTimestamp("created_at")));
    }

}
