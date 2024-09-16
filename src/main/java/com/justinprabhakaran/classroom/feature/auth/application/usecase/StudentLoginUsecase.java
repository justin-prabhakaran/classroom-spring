package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Slf4j
@Component
public class StudentLoginUsecase implements Usecase<StudentLoginParams, StudentModel> {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public StudentModel execute(StudentLoginParams args) {
        Optional<StudentModel> std ;

        log.info("usecase execute() Called..");
        if(args.getRegno()!=0){
            std = authRepository.getStudent(args.getRegno());
            log.info("authrepository of student called with Regno and Pass");
        }
        else{
            std = authRepository.getStudent(args.getEmail());
            log.info("authrepository of student called with Email and Pass");
        }

        if(std.isPresent()){
            log.info("Student Details Found in DB..");
            var student = std.get();

//            System.out.println(student.getPassHash() + "   " + args.getPass());
            if(passwordEncoder.matches(args.getPass(),student.getPassHash())){
                log.info("Student Login Password Matches..");
                return student;
            }
            else{
                log.warn("Student Login Password Mismatch..");
                throw new RuntimeException("Invalid Register Number/Email Or Password !!");
            }

//            return student;
        }
        log.error("Student Details Not Found in DB..");
        throw new RuntimeException("Student Not Found !!");
    }
}

