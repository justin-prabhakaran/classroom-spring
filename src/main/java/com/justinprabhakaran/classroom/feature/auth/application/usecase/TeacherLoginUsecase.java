package com.justinprabhakaran.classroom.feature.auth.application.usecase;

import com.justinprabhakaran.classroom.core.utils.Usecase;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TeacherLoginUsecase implements Usecase<TeacherLoginParams, Teacher> {
    @Autowired
    private AuthRepository repository;

    @Override
    public Teacher execute(TeacherLoginParams args) {
        Optional<TeacherModel> teacherModel =  repository.getTeacher(args.getEmail());
        if(teacherModel.isPresent()){
           var teacher = teacherModel.get();

           if(teacher.getPassHash().equals(args.getPass()))
               return teacher;
           else throw new RuntimeException("Invalid Register Number/Password !!");
        }
        throw new RuntimeException("Teacher Not Found!!");
    }
}


