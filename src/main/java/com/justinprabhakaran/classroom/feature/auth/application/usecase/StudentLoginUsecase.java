package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentLoginUsecase implements Usecase<StudentLoginParams, StudentModel> {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public StudentModel execute(StudentLoginParams args) {
        Optional<StudentModel> std ;

        if(args.getRegno()!=0){
            std = authRepository.getStudent(args.getRegno());
        }
        else{
            std = authRepository.getStudent(args.getEmail());
        }

        if(std.isPresent()){
            var student = std.get();
            System.out.println(student.getPassHash() + "   " + args.getPass());
            if(passwordEncoder.matches(args.getPass(),student.getPassHash())) return student;
            else throw new RuntimeException("Invalid Register Number/Email Or Password !!");

//            return student;
        }
        throw new RuntimeException("Student Not Found !!");
    }
}

