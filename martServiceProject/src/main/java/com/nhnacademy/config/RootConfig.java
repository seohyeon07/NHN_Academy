package com.nhnacademy.config;


import com.nhnacademy.Base;
import com.nhnacademy.repository.AnswerRepository;
import com.nhnacademy.repository.AnswerRepositoryImpl;
import com.nhnacademy.repository.PostRepository;
import com.nhnacademy.repository.PostRepositoryImpl;
import com.nhnacademy.repository.UserRepository;
import com.nhnacademy.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.register("admin", "12345", "csManager", "manager");
        userRepository.register("kim", "11111", "kim", "customer");
        userRepository.register("Lee", "22222", "Lee", "customer");

        return userRepository;
    }

    @Bean
    public PostRepository postRepository() {
        PostRepository postRepository = new PostRepositoryImpl();

        postRepository.register("kim", "test", "불만접수", "test", null);
        postRepository.register("Lee", "test1", "칭찬해요", "test1", null);
        return postRepository;
    }

    @Bean
    public AnswerRepository answerRepository() {
        return new AnswerRepositoryImpl();
    }

}
