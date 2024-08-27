package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherLoginParams {
    String email;
    String pass;
}
