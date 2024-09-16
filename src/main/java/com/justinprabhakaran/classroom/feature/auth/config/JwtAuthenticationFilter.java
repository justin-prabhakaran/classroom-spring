package com.justinprabhakaran.classroom.feature.auth.config;

import com.justinprabhakaran.classroom.feature.auth.application.service.JWTService;
import com.justinprabhakaran.classroom.feature.auth.application.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getParameterMap().toString());

        log.info("Request Method type  : {}", request.getMethod());
        log.info("Request Protocol     : {}", request.getProtocol());
        log.info("Request URL          : {}", request.getRequestURL());
//
//        BufferedReader br = request.getReader();
//        String l;
//
//        log.info("Request Parameters : ");
//        while((l = br.readLine())!=null){
//            log.info(l);
//        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("{} : {}", headerName, headerValue);
        }

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String jwt = authHeader.substring(7);

        String username = jwtService.extractUsername(jwt);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails;
            if(username.contains("@")){
                try {
                    userDetails = userDetailsService.loadUserByEmailStudent(username);
                }catch (UsernameNotFoundException e){
                    userDetails = userDetailsService.loadUserByEmailTeacher(username);
                }
            }else{
                userDetails = userDetailsService.loadUserByRegNo(username);
            }

            if(userDetails != null && jwtService.isTokenValid(jwt)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
