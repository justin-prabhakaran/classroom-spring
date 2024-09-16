package com.justinprabhakaran.classroom.feature.auth.presentation.controller;

import com.justinprabhakaran.classroom.feature.auth.application.service.AuthService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthController {


    @Autowired
    private AuthService service;

    @PostMapping("/login/student")
    public ResponseEntity<?> login(@RequestBody StudentLoginRequest loginRequest){
        if(loginRequest.getRegno()!=0)
            log.debug("/login/student endpoint called with Regno : "+loginRequest.getRegno()+" and Password : "+loginRequest.getPass());
        else
            log.debug("/login/student endpoint called with Email : "+loginRequest.getEmail()+" and Password : "+loginRequest.getPass());

        return service.studentLogin(loginRequest.getRegno(),loginRequest.getPass(),loginRequest.getEmail());

    }

    @PostMapping("/login/teacher")
    public ResponseEntity<?> login(@RequestBody TeacherLoginRequest loginRequest){

        log.debug("/login/teacher endpoint called. ");

        return service.teacherLogin(loginRequest.getEmail(),loginRequest.getPass());
    }

    @GetMapping("/teacher")
    public ResponseEntity<?> getTeacher(){
        log.debug("/teacher called.");
        return ResponseEntity.ok("welcome teacher !!!");
    }

    @GetMapping("/home/admin")
    public ResponseEntity<?> getAdmin() {
        log.debug("/home/admin endpoint called.");
        return ResponseEntity.ok("welcome admin !!!");
    }

    @GetMapping("/home")
    public ResponseEntity<?> getHome(){
        log.debug("/home/admin endpoint called.");
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


