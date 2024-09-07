package com.justinprabhakaran.classroom.feature.auth.presentation.controller;

import com.justinprabhakaran.classroom.feature.auth.application.service.AuthService;
import com.justinprabhakaran.classroom.feature.auth.application.service.JWTService;
import com.justinprabhakaran.classroom.feature.auth.application.service.MyUserDetailsService;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.presentation.dto.StudentLoginResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {


    @Autowired
    private AuthService service;

    @PostMapping("/login/student")
    public ResponseEntity<?> login(@RequestBody StudentLoginRequest loginRequest){
        System.out.println(loginRequest.regno);
        return service.studentLogin(loginRequest.getRegno(),loginRequest.getPass(),loginRequest.getEmail());

    }

    @PostMapping("/login/teacher")
    public ResponseEntity<?> login(@RequestBody TeacherLoginRequest loginRequest){
        return service.teacherLogin(loginRequest.getEmail(),loginRequest.getPass());
    }

    @GetMapping("/teacher")
    public ResponseEntity<?> getTeacher(){
        return ResponseEntity.ok("welcome teacher !!!");
    }

    @GetMapping("/home/admin")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok("welcome admin !!!");
    }

    @GetMapping("/home")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok("welcome home !!!");
    }



    @Getter
    @Setter
   static private class StudentLoginRequest {
        private long regno;
        private String pass;
        private String email;
    }

    @Getter
    @Setter
   static private class TeacherLoginRequest{
        private String email;
        private String pass;
    }
}


