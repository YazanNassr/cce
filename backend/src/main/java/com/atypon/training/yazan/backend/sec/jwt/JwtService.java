package com.atypon.training.yazan.backend.sec.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATIONTIME = 1800_000;
    static final String PREFIX = "Bearer";

    // TODO read key from configurations
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(key).compact();
        return token;
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return getAuthUser(token);
    }


    public String getAuthUser(String token) {
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody().getSubject();
            if (user != null)
                return user;
        }
        return null;
    }
}
