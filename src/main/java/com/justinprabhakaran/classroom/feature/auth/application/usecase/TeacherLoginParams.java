package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherLoginParams {
    private String email;
    private String pass;
}
