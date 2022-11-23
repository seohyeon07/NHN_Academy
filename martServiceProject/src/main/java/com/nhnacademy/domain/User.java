package com.nhnacademy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private final String id;


    private final String pwd;

    private final String name;

    private String role;

}
