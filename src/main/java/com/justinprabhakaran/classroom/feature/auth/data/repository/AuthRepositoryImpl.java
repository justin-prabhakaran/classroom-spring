package com.justinprabhakaran.classroom.feature.auth.data.repository;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.datasource.TeacherAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
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
    public Optional<StudentModel> getStudent(Long registerNumber) {
        return studentAuthDataSource.findById(registerNumber);
    }

    @Override
    public Optional<TeacherModel> getTeacher(String email) {
        return teacherAuthDataSource.findByEmail(email);
    }

}
