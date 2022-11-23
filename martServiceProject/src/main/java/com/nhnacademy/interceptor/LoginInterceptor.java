package com.nhnacademy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        Object studentId = request.getSession().getAttribute("id");

        if (studentId == null) {
            response.sendRedirect("/cs/login");
            return false;
        }
        return true;
    }
}
