package com.justinprabhakaran.classroom.core.utils;

import lombok.Getter;

@Getter
public enum DEPARTMENT {

    CSE("cse"),
    ECE("ece"),
    EEE("eee"),
    MECH("mech"),
    CIVIL("civil");

    private final String name;

    DEPARTMENT(String name){
        this.name = name;
    }
}
