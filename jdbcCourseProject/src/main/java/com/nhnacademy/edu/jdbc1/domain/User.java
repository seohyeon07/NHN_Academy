package com.nhnacademy.edu.jdbc1.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class User {
    private final int id;
    private final String username;
    private final String password;
    private final Date createdAt;


}
