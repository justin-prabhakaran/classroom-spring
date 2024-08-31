package com.justinprabhakaran.classroom.feature.auth.domain.repository;



import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;


import java.util.Optional;


public interface AuthRepository {

    Optional<StudentModel> getStudent(Long registerNumber);
    Optional<StudentModel> getStudent(String email);
    Optional<TeacherModel> getTeacher(String email);
}
