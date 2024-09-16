package com.justinprabhakaran.classroom.feature.auth.config;

import com.justinprabhakaran.classroom.feature.auth.application.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.debug("Authentication processing..");
        UserDetails userDetails;
        if(isRegNo(username)){
            userDetails = userDetailsService.loadUserByRegNo(username);
        }else if(username.contains("@")) {
            try {
                userDetails = userDetailsService.loadUserByEmailStudent(username);
            }
            catch (UsernameNotFoundException e){

                userDetails = userDetailsService.loadUserByEmailTeacher(username);
            }
        }else{
            log.error("Invalid Credentials..");
            throw new BadCredentialsException("Invalid Credentials !!");
        }

        if(passwordEncoder.matches(password,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetailsService,password,userDetails.getAuthorities());
        }else{
            log.error("Invalid Credentials..");
            throw new BadCredentialsException("Invalid Credentials !!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isRegNo(String str) {
        log.debug("Checking RegisterNumber is valid or not!");
        return str.startsWith("953");
    }
}
