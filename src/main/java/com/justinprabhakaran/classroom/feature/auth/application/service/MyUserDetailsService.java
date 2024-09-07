package com.justinprabhakaran.classroom.feature.auth.application.service;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.datasource.TeacherAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService  {
    @Autowired
    StudentAuthDataSource studentAuthDataSource;

    @Autowired
    TeacherAuthDataSource teacherAuthDataSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //let it be empty
       return null;
    }

    public UserDetails loadUserByEmailStudent(String email) throws UsernameNotFoundException{
        System.out.println("CALLED " + email);
        Optional<StudentModel> student = studentAuthDataSource.findByEmail(email);
        if(student.isPresent()){
            System.out.println(student + "IS PRESENT");
            StudentModel studentModel = student.get();
            System.out.println(studentModel);
            return User.builder()
                    .username(studentModel.getEmail())
                    .password(studentModel.getPassHash())
                    .roles(studentModel.getSecurityRole().toString())
                    .build();

        }else throw new UsernameNotFoundException("Email Not found !!" + email);
    }

    public UserDetails loadUserByRegNo(String regno) throws UsernameNotFoundException{
        Optional<StudentModel> student = studentAuthDataSource.findById(Long.parseLong(regno));
        if(student.isPresent()){
            StudentModel studentModel = student.get();
            return User.builder()
                    .username(studentModel.getRegisterNumber().toString())
                    .password(studentModel.getPassHash())
                    .roles(studentModel.getSecurityRole().toString())
                    .build();

        }else{
            throw new UsernameNotFoundException("RegisterNumber Not found !!" + regno);
        }
    }

    public UserDetails loadUserByEmailTeacher(String email){
        System.out.println(email);
        Optional<TeacherModel> teacher = teacherAuthDataSource.findByEmail(email);

        if(teacher.isPresent()){
            TeacherModel teacherModel = teacher.get();
            return User.builder()
                    .username(teacherModel.getEmail())
                    .password(teacherModel.getPassHash())
                    .roles(teacherModel.getSecurityRole().toString())
                    .build();
        }
        else{
            throw new UsernameNotFoundException("Email Not found !!" + email );
        }
    }


}
