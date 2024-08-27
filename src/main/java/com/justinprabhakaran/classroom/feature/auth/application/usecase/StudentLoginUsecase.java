package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentLoginUsecase implements Usecase<StudentLoginParams, Student> {
    @Autowired
    private AuthRepository authRepository;

    @Override
    public Student execute(StudentLoginParams args) {
        Optional<Student> std = authRepository.getStudent(args.regno,args.pass);

        if(std.isPresent()){
            return std.get();
        }
        throw new RuntimeException("Not Found !!");
    }
}

