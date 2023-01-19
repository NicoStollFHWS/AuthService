package com.nicolas.stoll.authservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = SecretKeyProvider.getKey();
    private final long VALID_TIME_MS = 3600000;


    public String createToken(String username) {
        Claims claims = Jwts.claims()
                .setSubject(username);


        Date now = new Date();
        Date validUntil = new Date(now.getTime() + VALID_TIME_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("typ","JWT")
                .setIssuer("AuthService")
                .setIssuedAt(now)
                .setExpiration(validUntil)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
