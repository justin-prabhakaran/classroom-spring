package com.justinprabhakaran.classroom.feature.auth.data.repository;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.datasource.TeacherAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class AuthRepositoryImpl  implements AuthRepository {
    @Autowired
    StudentAuthDataSource studentAuthDataSource;
    @Autowired
    TeacherAuthDataSource teacherAuthDataSource;

    @Override
    public Optional<StudentModel> getStudent(Long registerNumber) {
        log.debug("getStudent() using register number called..");
        return studentAuthDataSource.findById(registerNumber);
    }

    @Override
    public Optional<StudentModel> getStudent(String email) {
        log.debug("getStudent() using email called..");
        return studentAuthDataSource.findByEmail(email);
    }

    @Override
    public Optional<TeacherModel> getTeacher(String email) {
        log.debug("getTeacher() using email called..");
        return teacherAuthDataSource.findByEmail(email);
    }

}
