package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
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
        Optional<StudentModel> std = authRepository.getStudent(args.getRegno());

        if(std.isPresent()){
            var student = std.get();

            if(student.getPassHash().equals(args.getPass()))
                return student;
            else throw new RuntimeException("Invalid Register Number/Email Or Password !!");
        }
        throw new RuntimeException("Student Not Found !!");
    }
}

