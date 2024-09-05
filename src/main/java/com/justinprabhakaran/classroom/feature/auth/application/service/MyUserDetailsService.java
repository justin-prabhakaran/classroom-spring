package com.justinprabhakaran.classroom.feature.auth.application.service;

import com.justinprabhakaran.classroom.feature.auth.data.datasource.StudentAuthDataSource;
import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
            throw new UsernameNotFoundException("RegisterNumber Not found !!" + student.toString());
        }

    }


}
