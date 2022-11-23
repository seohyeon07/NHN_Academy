package com.nhnacademy.edu.jdbc1.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Subject {
    private final int id;
    private final String name;
    private final Date createdAt;

}
