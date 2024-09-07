package com.justinprabhakaran.classroom.feature.auth.application.service;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    @Autowired
    StudentAuthDataSource studentAuthDataSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StudentModel> student = studentAuthDataSource.findById(Long.parseLong(username));
        if(student.isPresent()){
            StudentModel studentModel = student.get();
            return User.builder()
                    .username(studentModel.getRegisterNumber().toString())
                    .password(studentModel.getPassHash())
                    .roles(studentModel.getSecurityRole().toString())
                    .build();

        }else{
            throw new UsernameNotFoundException("RegisterNumber Not found !!" );
        }

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
            throw new UsernameNotFoundException("RegisterNumber Not found !!" );
        }
    }


}
