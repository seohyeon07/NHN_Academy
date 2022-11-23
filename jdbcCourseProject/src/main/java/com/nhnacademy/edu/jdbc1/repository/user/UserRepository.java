package com.nhnacademy.edu.jdbc1.repository.user;

import com.nhnacademy.edu.jdbc1.domain.User;

public interface UserRepository {
    User findById(String userName);

}
