package com.justinprabhakaran.classroom.feature.auth.application.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.StudentLoginParams;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.StudentLoginUsecase;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.TeacherLoginParams;
import com.justinprabhakaran.classroom.feature.auth.application.usecase.TeacherLoginUsecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.presentation.dto.StudentLoginResponse;
import com.justinprabhakaran.classroom.feature.auth.presentation.dto.TeacherLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    MyUserDetailsService userDetailsService;


    @Autowired
    private TeacherLoginUsecase teacherLoginUsecase;


    @Autowired
    private StudentLoginUsecase studentLoginUsecase;
    public ResponseEntity<?> studentLogin(long regno,String pass,String email){
        try {
            var studentLoginParams = new StudentLoginParams();
            studentLoginParams.setPass(pass);
            studentLoginParams.setRegno(regno);
            studentLoginParams.setEmail(email);

            StudentModel student = studentLoginUsecase.execute(studentLoginParams);

            StudentLoginResponse response = new StudentLoginResponse();
            if(regno != 0){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                Long.toString(regno),
                                pass
                        )
                );

                if(authentication.isAuthenticated()){
                    String jwt = jwtService.generateToken(userDetailsService.loadUserByRegNo(
                            Long.toString(regno)
                    ));
                    response.setJwt(jwt);
                }
                else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");
            }else if(!email.isEmpty()){
                System.out.println(email);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email,
                                pass
                        )
                );
                if(authentication.isAuthenticated()){
                    String jwt = jwtService.generateToken(userDetailsService.loadUserByEmailStudent(email));
                    response.setJwt(jwt);
                }
                else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");
            }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty Request idiot !!!");

            response.setName(student.getName());
            response.setEmail(student.getEmail());
            response.setDepartment(student.getDepartment());
            response.setSection(student.getSection());
            response.setYear(student.getYear());
            response.setSecurityRole(student.getSecurityRole());
            response.setRegisterNumber(student.getRegisterNumber());
            response.setSecurityRole(student.getSecurityRole());

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

    }

    public ResponseEntity<?> teacherLogin(String email,String pass){
        try {
            var teacherLoginParams = new TeacherLoginParams();
            teacherLoginParams.setPass(pass);
            teacherLoginParams.setEmail(email);

            TeacherModel teacher = teacherLoginUsecase.execute(teacherLoginParams);

            TeacherLoginResponse response = new TeacherLoginResponse();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            pass
                    )
            );
            if(authentication.isAuthenticated()){
                String jwt = jwtService.generateToken(userDetailsService.loadUserByEmailTeacher(email));
                response.setJwt(jwt);
            }
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");

            response.setTeacherId(teacher.getTeacherId());
            response.setEmail(teacher.getEmail());
            response.setName(teacher.getName());
            response.setDepartment(teacher.getDepartment());
            response.setRole(teacher.getRole());
            response.setSecurityRole(teacher.getSecurityRole());
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }


}
