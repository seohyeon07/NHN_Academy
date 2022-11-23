package com.nhnacademy.edu.jdbc1.controller;

import com.nhnacademy.edu.jdbc1.service.login.UserLoginService;
import com.nhnacademy.edu.jdbc1.repository.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserLoginService loginService;


    public LoginController(UserRepository userRepository, UserLoginService loginService) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(@SessionAttribute(required = false) String id) {
        if (id != null) {
            return "redirect:/";
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request) {
        if (!loginService.matches(id, pwd)) {
            return "redirect:/login";
        }
        request.getSession().setAttribute("id",id);
        return "redirect:/courses";

    }
}
