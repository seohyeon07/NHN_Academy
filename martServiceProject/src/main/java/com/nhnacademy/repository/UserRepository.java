package com.nhnacademy.repository;


import com.nhnacademy.domain.User;

public interface UserRepository {

    User getUser(String id);

    User register(String id, String pwd, String name, String role);

}
