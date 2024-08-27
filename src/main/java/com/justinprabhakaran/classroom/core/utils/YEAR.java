package com.justinprabhakaran.classroom.core.utils;

import lombok.Getter;

@Getter
public enum YEAR {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final int value;
    YEAR(int value) {
        this.value = value;
    }

}
