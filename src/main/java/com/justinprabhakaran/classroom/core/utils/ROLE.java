package com.justinprabhakaran.classroom.core.utils;

import lombok.Getter;

@Getter
public enum ROLE {

    HOD(1),
    ASSOCIATE(2),
    ASSISTANT(3);

    private final int value;
    ROLE(int value){
        this.value = value;
    }
}
