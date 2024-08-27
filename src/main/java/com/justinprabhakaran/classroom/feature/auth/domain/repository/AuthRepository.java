package com.justinprabhakaran.classroom.feature.auth.domain.repository;



import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface AuthRepository {

    Optional<Student> getStudent(Long registerNumber,String pass);
    Optional<Teacher> getTeacher(Long registerNumber);
}
