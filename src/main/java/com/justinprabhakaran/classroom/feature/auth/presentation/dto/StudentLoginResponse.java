package com.justinprabhakaran.classroom.feature.auth.presentation.dto;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.SROLE;
import com.justinprabhakaran.classroom.core.utils.YEAR;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginResponse {

    protected Long registerNumber;

    protected String name;

    protected String email;

    protected YEAR year;

    protected DEPARTMENT department;

    protected Character section;

    protected SROLE securityRole;

    protected String jwt;
}
