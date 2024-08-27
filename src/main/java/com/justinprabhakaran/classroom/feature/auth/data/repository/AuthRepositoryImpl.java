package com.justinprabhakaran.classroom.feature.auth.data.repository;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.datasource.TeacherAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AuthRepositoryImpl  implements AuthRepository {
    @Autowired
    StudentAuthDataSource studentAuthDataSource;
    @Autowired
    TeacherAuthDataSource teacherAuthDataSource;

    @Override
    public Optional<Student> getStudent(Long registerNumber,String pass) {
        return studentAuthDataSource.findById(registerNumber).map(studentModel ->(Student) studentModel);
    }

    @Override
    public Optional<Teacher> getTeacher(Long registerNumber) {
        return Optional.empty();
    }


    //Todo: implementation needed
}
