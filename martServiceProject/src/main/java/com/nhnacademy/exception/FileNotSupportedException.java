package com.nhnacademy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class FileNotSupportedException extends RuntimeException {

    private final HttpStatus status;
}
