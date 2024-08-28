package com.justinprabhakaran.classroom.feature.auth.config;

import com.justinprabhakaran.classroom.feature.auth.data.repository.AuthRepositoryImpl;
import com.justinprabhakaran.classroom.feature.auth.domain.repository.AuthRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AuthConfig {
    @Primary
    @Bean
    public AuthRepository authRepository(){
        return new AuthRepositoryImpl();
    }
}
