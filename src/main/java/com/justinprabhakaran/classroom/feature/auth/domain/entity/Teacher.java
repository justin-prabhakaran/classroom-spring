package com.justinprabhakaran.classroom.feature.auth.domain.entity;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.ROLE;
import lombok.AllArgsConstructor;


public class Teacher {
    protected String name;
    protected String email;
    protected DEPARTMENT department;
    protected ROLE role;
}
