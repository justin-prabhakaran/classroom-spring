package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetStudentWithUsernameUsecase implements Usecase<GetStudentWithUsernameParams, StudentModel> {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public StudentModel execute(GetStudentWithUsernameParams args) {
        String username = args.getUsername();
        Optional<StudentModel> studentModel;
        if(username.contains("@")){
            studentModel = authRepository.getStudent(username);
        }else{
            studentModel = authRepository.getStudent(Long.parseLong(username));
        }
        if(studentModel.isPresent()){
            return studentModel.get();
        }else throw new UsernameNotFoundException("User Not Found !!" + username);
    }
}
