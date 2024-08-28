package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginParams{
    private long regno;
    private String pass;

}
