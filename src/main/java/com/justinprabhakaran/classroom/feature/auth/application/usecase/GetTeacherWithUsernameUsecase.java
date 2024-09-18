package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetTeacherWithUsernameUsecase implements Usecase<GetTeacherWithUsernameParams, TeacherModel> {

    @Autowired
    AuthRepository authRepository;

    @Override
    public TeacherModel execute(GetTeacherWithUsernameParams args) {

        String username  = args.getUsername();

        Optional<TeacherModel> teacherModel = authRepository.getTeacher(username);

        if(teacherModel.isPresent()){
            return teacherModel.get();
        }
        else throw new UsernameNotFoundException("User Not Found !!" + username);

    }
}
