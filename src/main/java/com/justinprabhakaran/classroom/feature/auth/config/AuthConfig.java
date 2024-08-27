package com.justinprabhakaran.classroom.feature.auth.config;

import com.justinprabhakaran.classroom.feature.auth.data.repository.AuthRepositoryImpl;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    //Todo: implementation needed

    @Bean
    public AuthRepository authRepository(){
        return new AuthRepositoryImpl();
    }
}
