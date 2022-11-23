package com.nhnacademy.edu.jdbc1.repository.course;

import com.nhnacademy.edu.jdbc1.domain.CourseRequest;
import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import com.nhnacademy.edu.jdbc1.domain.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcCourseRepository implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Course> findAll() {
        String sql = "SELECT c.id as c_id, c.teacher_id as c_teacher_id, c.subject_id as c_subject_id, c.created_at as c_created_at, " +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at, " +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at " +
                "FROM JdbcCourses as c " +
                "INNER JOIN JdbcSubjects as s ON c.subject_id = s.id " +
                "INNER JOIN JdbcTeachers as t ON c.teacher_id = t.id;";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Teacher teacher = new Teacher(
                            resultSet.getInt("t_id"),
                            resultSet.getString("t_name"),
                            resultSet.getTimestamp("t_created_at")
                    );
                    Subject subject = new Subject(
                            resultSet.getInt("s_id"),
                            resultSet.getString("s_name"),
                            resultSet.getTimestamp("s_created_at")
                    );
                    return new Course(resultSet.getInt("c_id"),
                            teacher,
                            subject,
                            resultSet.getTimestamp("c_created_at"));
                }

        );

    }

    @Override
    public Course findById(int id) {

        String sql = "SELECT c.id as c_id, c.teacher_id as c_teacher_id, c.subject_id as c_subject_id, c.created_at as c_created_at, " +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at, " +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at " +
                "FROM JdbcCourses as c " +
                "INNER JOIN JdbcSubjects as s ON c.subject_id = s.id " +
                "INNER JOIN JdbcTeachers as t ON c.teacher_id = t.id " +
                "WHERE c.id = " + id;

        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    Teacher teacher = new Teacher(
                            resultSet.getInt("t_id"),
                            resultSet.getString("t_name"),
                            resultSet.getTimestamp("t_created_at")
                    );
                    Subject subject = new Subject(
                            resultSet.getInt("s_id"),
                            resultSet.getString("s_name"),
                            resultSet.getTimestamp("s_created_at")
                    );
                    return new Course(resultSet.getInt("c_id"),
                            teacher,
                            subject,
                            resultSet.getTimestamp("c_created_at"));
                }
        );
    }

    @Override
    @Transactional
    public void insert(CourseRequest courseRequest) {
        jdbcTemplate.update(
                "insert into JdbcCourses(teacher_id, subject_id, created_at) values (?,?,?)",
                courseRequest.getTeacherId(),
                courseRequest.getSubjectId(),
                new Timestamp(new Date().getTime())
        );
    }

    @Override
    @Transactional
    public void update(CourseRequest courseRequest) {
        jdbcTemplate.update(
                "update JdbcCourses set teacher_id =?, subject_id=? where id=?",
                courseRequest.getTeacherId(),courseRequest.getSubjectId(),courseRequest.getId()
        );

    }

    @Override
    @Transactional
    public void delete(int id){
        jdbcTemplate.update(
                "delete from JdbcCourses where id=?",id);
    }

}
