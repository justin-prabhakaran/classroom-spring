package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TeacherLoginUsecase implements Usecase<TeacherLoginParams, Teacher> {
    @Autowired
    private AuthRepository repository;

    @Override
    public Teacher execute(TeacherLoginParams args) {
        Optional<Teacher> teacher =  repository.getTeacher(args.getEmail(), args.getPass());
        if(teacher.isPresent()){
            return teacher.get();
        }
        throw new RuntimeException("Teacher Not Found!!");
    }
}


