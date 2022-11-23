package com.nhnacademy.exception;

public class PostNotExistException extends RuntimeException {

    public PostNotExistException(String message) {
        super(message);
    }
}
