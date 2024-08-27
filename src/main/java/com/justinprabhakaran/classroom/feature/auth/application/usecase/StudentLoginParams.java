package com.justinprabhakaran.classroom.feature.auth.application.usecase;

public class StudentLoginParams{
    long regno;
    String pass;

    public StudentLoginParams(long regno, String pass) {
        this.regno = regno;
        this.pass = pass;
    }
}
