package com.justinprabhakaran.classroom.feature.auth.presentation;

import com.justinprabhakaran.classroom.feature.auth.application.service.AuthService;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
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

    @PostMapping("/login")
    public ResponseEntity<Student> login(@RequestBody LoginRequest loginRequest){
        return service.studentLogin(loginRequest.getRegno(),loginRequest.getPass());
    }

}

@Getter
@Setter
class LoginRequest {
    private long regno;
    private String pass;
}
