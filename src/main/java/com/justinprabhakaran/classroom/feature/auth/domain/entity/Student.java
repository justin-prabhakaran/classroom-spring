package com.justinprabhakaran.classroom.feature.auth.domain.entity;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.YEAR;

public class Student {
    protected Long registerNumber;
    protected String name;
    protected String email;
    protected String passHash;
    protected YEAR year;
    protected DEPARTMENT dept;
    protected Character section;
}
