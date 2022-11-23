package com.nhnacademy.edu.jdbc1.service.login;

import javax.servlet.http.HttpServletRequest;

public interface UserLoginService {
    boolean matches(String userName, String pwd);


}
