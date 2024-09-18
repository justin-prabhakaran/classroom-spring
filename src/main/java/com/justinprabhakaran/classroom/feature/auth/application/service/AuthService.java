package com.justinprabhakaran.classroom.feature.auth.application.service;

import com.justinprabhakaran.classroom.feature.auth.application.usecase.*;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.presentation.dto.StudentLoginResponse;
import com.justinprabhakaran.classroom.feature.auth.presentation.dto.TeacherLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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

    @Autowired
    private GetStudentWithUsernameUsecase getStudentWithUsernameUsecase;

    @Autowired
    private GetTeacherWithUsernameUsecase getTeacherWithUsernameUsecase;

    public ResponseEntity<?> studentLogin(long regno,String pass,String email){
        try {
            log.info("studentLogin service called ");
            var studentLoginParams = new StudentLoginParams();
            studentLoginParams.setPass(pass);
            studentLoginParams.setRegno(regno);
            studentLoginParams.setEmail(email);
            log.debug("RequestBody Parameters passed to StudentLoginParams class successfully.");
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
                    log.info("Authentication Success.");
                    String jwt = jwtService.generateToken(userDetailsService.loadUserByRegNo(
                            Long.toString(regno)
                    ));
                    log.info("Jwt Generated.");
                    response.setJwt(jwt);
                }
                else{
                    log.error("Authentication Failed..");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");
                }
            }else if(!email.isEmpty()){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email,
                                pass
                        )
                );
                if(authentication.isAuthenticated()){
                    log.info("Authentication Success.");
                    String jwt = jwtService.generateToken(userDetailsService.loadUserByEmailStudent(email));
                    log.info("Jwt Generated.");
                    response.setJwt(jwt);
                }
                else{
                    log.error("Authentication Failed..");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");
                }
            }else{
                log.error("Empty Request..");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty Request !!!");
            }

            response.setName(student.getName());
            response.setEmail(student.getEmail());
            response.setDepartment(student.getDepartment());
            response.setSection(student.getSection());
            response.setYear(student.getYear());
            response.setSecurityRole(student.getSecurityRole());
            response.setRegisterNumber(student.getRegisterNumber());
            response.setSecurityRole(student.getSecurityRole());

            log.info("studentLogin Response generated successfully..");
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            log.error("Error : {}", String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

    }

    public ResponseEntity<?> teacherLogin(String email,String pass){
        try {
            log.info("teacherLogin service called : {}", email);
            var teacherLoginParams = new TeacherLoginParams();
            teacherLoginParams.setPass(pass);
            teacherLoginParams.setEmail(email);
            log.debug("RequestBody Parameters passed to StudentLoginParams class successfully.");
            TeacherModel teacher = teacherLoginUsecase.execute(teacherLoginParams);

            TeacherLoginResponse response = new TeacherLoginResponse();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            pass
                    )
            );
            if(authentication.isAuthenticated()){
                log.info("Authentication Success.");
                String jwt = jwtService.generateToken(userDetailsService.loadUserByEmailTeacher(email));
                response.setJwt(jwt);
            }
            else{
                log.error("Authentication Failed..");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed !!");
            }

            response.setTeacherId(teacher.getTeacherId());
            response.setEmail(teacher.getEmail());
            response.setName(teacher.getName());
            response.setDepartment(teacher.getDepartment());
            response.setRole(teacher.getRole());
            response.setSecurityRole(teacher.getSecurityRole());
            log.info("treacherLogin Response generated successfully..");
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            log.error("Error : {}", String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    public ResponseEntity<?> getCurrentUser(Authentication authentication){
        String username = authentication.getPrincipal().toString();

        try{
            StudentModel studentModel = getStudentWithUsernameUsecase.execute(new GetStudentWithUsernameParams(username));

            StudentLoginResponse studentLoginResponse = getStudentResponse(studentModel);

            return ResponseEntity.ok().body(studentLoginResponse);
        }catch (UsernameNotFoundException e) {
            try {

                TeacherModel teacherModel = getTeacherWithUsernameUsecase.execute(new GetTeacherWithUsernameParams(username));

                TeacherLoginResponse teacherLoginResponse = getTeacherResponse(teacherModel);

                return ResponseEntity.ok().body(teacherLoginResponse);
            }
            catch (UsernameNotFoundException e1){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


    private StudentLoginResponse getStudentResponse(StudentModel studentModel){
        StudentLoginResponse response = new StudentLoginResponse();
        response.setName(studentModel.getName());
        response.setSection(studentModel.getSection());
        response.setYear(studentModel.getYear());
        response.setEmail(studentModel.getEmail());
        response.setDepartment(studentModel.getDepartment());
        response.setRegisterNumber(studentModel.getRegisterNumber());
        response.setSecurityRole(studentModel.getSecurityRole());
        return response;
    }

    private  TeacherLoginResponse getTeacherResponse(TeacherModel teacherModel){
        TeacherLoginResponse response = new TeacherLoginResponse();
        response.setTeacherId(teacherModel.getTeacherId());
        response.setName(teacherModel.getName());
        response.setEmail(teacherModel.getEmail());
        response.setRole(teacherModel.getRole());
        response.setSecurityRole(teacherModel.getSecurityRole());
        response.setDepartment(teacherModel.getDepartment());

        return response;
    }


}
