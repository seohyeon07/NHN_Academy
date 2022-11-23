package com.nhnacademy.controller;


import com.nhnacademy.domain.User;
import com.nhnacademy.exception.LoginFailException;
import com.nhnacademy.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/cs")
public class LoginController {

    private final UserRepository userRepository;


    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User user = userRepository.getUser(id);
        if (user == null || !user.getPwd().equals(pwd)) {
            throw new LoginFailException("Bad Access!");
        }
        request.getSession().setAttribute("id", id);

        String role = user.getRole();

        if (role.equals("manager")) {
            return "redirect:/cs/admin";
        }
        return "redirect:/cs/";
    }

}
