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
            var studentLoginParams = new StudentLoginParams();
            studentLoginParams.setPass(pass);
            studentLoginParams.setRegno(regno);

            Student student = studentLoginUsecase.execute(studentLoginParams);

            return ResponseEntity.ok().body(student);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Autowired
    private TeacherLoginUsecase teacherLoginUsecase;
    public ResponseEntity<Teacher> teacherLogin(String email,String pass){
        try {
            var teacherLoginParams = new TeacherLoginParams();
            teacherLoginParams.setPass(pass);
            teacherLoginParams.setEmail(email);

            Teacher teacher = teacherLoginUsecase.execute(teacherLoginParams);

            return ResponseEntity.ok().body(teacher);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
