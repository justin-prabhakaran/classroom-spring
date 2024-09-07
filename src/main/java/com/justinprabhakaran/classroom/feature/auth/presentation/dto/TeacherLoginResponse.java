package com.justinprabhakaran.classroom.feature.auth.presentation.dto;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.ROLE;
import com.justinprabhakaran.classroom.core.utils.SROLE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherLoginResponse {

    protected long teacherId;

    protected String name;

    protected String email;

    protected DEPARTMENT department;

    protected ROLE role;

    protected String passHash;

    protected SROLE securityRole;

    protected String jwt;
}
