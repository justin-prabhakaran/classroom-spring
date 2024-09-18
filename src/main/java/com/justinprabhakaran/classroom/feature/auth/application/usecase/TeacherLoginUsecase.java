package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Slf4j
@Component
public class TeacherLoginUsecase implements Usecase<TeacherLoginParams, Teacher> {
    @Autowired
    private AuthRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TeacherModel execute(TeacherLoginParams args) {
        Optional<TeacherModel> teacherModel =  repository.getTeacher(args.getEmail());
        log.info("usecase execute() Called..");
        if(teacherModel.isPresent()){
            log.info("Teacher Details Found in DB..");
           var teacher = teacherModel.get();

           if(passwordEncoder.matches(args.getPass(),teacher.getPassHash())) return teacher;
           else{
               log.warn("Teacher Login Password Mismatch..");
               throw new RuntimeException("Invalid Register Number/Email Or Password !!");
           }
        }
        log.error("Teacher Details Not Found in DB..");
        throw new RuntimeException("Teacher Not Found!!");
    }
}


