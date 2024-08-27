package com.justinprabhakaran.classroom.feature.auth.application.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.StudentLoginParams;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.StudentLoginUsecase;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.TeacherLoginParams;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.TeacherLoginUsecase;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StudentLoginUsecase studentLoginUsecase;
    public ResponseEntity<Student> studentLogin(long regno,String pass){
        try {
            Student student = studentLoginUsecase.execute(new StudentLoginParams(regno,pass));
            return ResponseEntity.ok().body(student);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Autowired
    private TeacherLoginUsecase teacherLoginUsecase;
    public ResponseEntity<Teacher> teacherLogin(String email,String pass){
        try {
            Teacher teacher = teacherLoginUsecase.execute(new TeacherLoginParams(email,pass));
            return ResponseEntity.ok().body(teacher);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
