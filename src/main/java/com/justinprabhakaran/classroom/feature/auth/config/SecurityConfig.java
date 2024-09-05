package com.justinprabhakaran.classroom.feature.auth.config;
import com.justinprabhakaran.classroom.feature.auth.application.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        registory ->{
                            registory.requestMatchers("/api/v1/login/**").permitAll();
                            registory.requestMatchers("/api/v1/home/admin").hasRole("ADMIN");
                            registory.requestMatchers("/api/v1/teacher").hasRole("TEACHER");
                            registory.anyRequest().authenticated();
                        }
                ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder( passwordEncoder() );

        return authenticationProvider;
    }

    @Bean AuthenticationManager authenticationManager(){
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        System.out.println(new BCryptPasswordEncoder().encode("bhuvi"));
        return new BCryptPasswordEncoder();
    }
}
