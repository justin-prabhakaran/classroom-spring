package com.justinprabhakaran.classroom.feature.auth.presentation;

import com.justinprabhakaran.classroom.feature.auth.application.service.AuthService;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login/student")
    public ResponseEntity<Student> login(@RequestBody StudentLoginRequest loginRequest){
        return service.studentLogin(loginRequest.getRegno(),loginRequest.getPass());
    }

    @PostMapping("/login/teacher")
    public ResponseEntity<Teacher> login(@RequestBody TeacherLoginRequest loginRequest){
        return service.teacherLogin(loginRequest.getEmail(),loginRequest.getPass());
    }


    @Getter
    @Setter
   private class StudentLoginRequest {
        private long regno;
        private String pass;
    }

    @Getter
    @Setter
    private class TeacherLoginRequest{
        private String email;
        private String pass;
    }
}


