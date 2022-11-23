package com.nhnacademy.edu.jdbc1.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        Object user = request.getSession().getAttribute("id");

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
