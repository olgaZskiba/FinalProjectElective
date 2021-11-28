package com.olgaskyba.elective.model;

public enum Role {

    ADMIN,
    TEACHER,
    STUDENT;

    public String getName(){
        return name().toLowerCase();
    }
}
