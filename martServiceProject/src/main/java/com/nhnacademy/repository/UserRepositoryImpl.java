package com.nhnacademy.repository;

import com.nhnacademy.domain.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public User register(String id, String pwd, String name, String role) {

        User user = User.builder().id(id).pwd(pwd).name(name).role(role).build();
        userMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

}
