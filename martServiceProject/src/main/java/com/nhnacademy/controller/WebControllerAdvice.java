package com.nhnacademy.controller;

import com.nhnacademy.exception.FileNotSupportedException;
import com.nhnacademy.exception.LoginFailException;
import com.nhnacademy.exception.PermissionDeniedException;
import com.nhnacademy.exception.PostNotExistException;
import com.nhnacademy.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<String> handleFileNotSupportedException(
            FileNotSupportedException exception) {
        log.error("", exception);
        return ResponseEntity.status(exception.getStatus()).body("File not supported!");
    }

    @ExceptionHandler(LoginFailException.class)
    public ModelAndView handleLoginFailException(LoginFailException exception) {
        ModelAndView mav = new ModelAndView("error", HttpStatus.BAD_REQUEST);
        mav.addObject("exception", exception.getMessage());
        return mav;
    }

    @ExceptionHandler({UserNotFoundException.class, PostNotExistException.class})
    public ModelAndView handleUserNotFoundException(RuntimeException exception) {
        ModelAndView mav = new ModelAndView("error", HttpStatus.NOT_FOUND);
        mav.addObject("exception", exception.getMessage());
        return mav;
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ModelAndView handlePermissionDeniedException(PermissionDeniedException exception) {
        ModelAndView mav = new ModelAndView("error", HttpStatus.FORBIDDEN);
        mav.addObject("exception", exception.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
        ModelAndView mav = new ModelAndView("error", HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("exception", exception.getMessage());

        return mav;
    }

}
