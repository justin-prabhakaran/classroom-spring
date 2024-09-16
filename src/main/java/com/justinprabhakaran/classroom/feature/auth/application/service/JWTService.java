package com.justinprabhakaran.classroom.feature.auth.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class JWTService {

    private static final String SECRET = "bfe12dc4df09f773262d9554222d2cb977f051a1c68c04be7fd5b5371645aa12";
    private static final long VALIDITY = TimeUnit.HOURS.toMillis(1);


    public String generateToken(UserDetails userDetails){
        log.debug("Generating Jwt");
        return  Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateSecretKey())
                .compact();
    }

    private SecretKey generateSecretKey(){
        log.debug("Generating SecretKey..");
       var key = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims getClaims(String jwt){
        log.debug("Getting claims from jwt");
        return  Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
    public String extractUsername(String jwt){
        log.debug("Getting username from jwt..");
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    public boolean isTokenValid(String jwt){
        log.debug("Checking Validity of Token..");
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
