package com.nhnacademy.edu.jdbc1.repository.user;

import com.nhnacademy.edu.jdbc1.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(String userName) {
        return jdbcTemplate.queryForObject(
                "select id, username, password, created_at from JdbcUsers where username =?",
                (resultSet, rowNum) ->
                        new User(resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getTimestamp("created_at")),userName
                );
    }
}

