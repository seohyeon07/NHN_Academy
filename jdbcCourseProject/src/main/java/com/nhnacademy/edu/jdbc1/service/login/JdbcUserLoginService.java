package com.nhnacademy.edu.jdbc1.service.login;

import com.nhnacademy.edu.jdbc1.domain.User;
import com.nhnacademy.edu.jdbc1.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JdbcUserLoginService implements UserLoginService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean matches(String userName, String pwd) {
        User user = userRepository.findById(userName);
        if (user == null || !user.getPassword().equals(pwd)) {
            return false;
        }
        return true;
    }

}
