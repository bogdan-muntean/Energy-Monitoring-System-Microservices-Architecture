package com.example.User_Management.entities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //10 minute  = 1000 ms * 60 sec * 60 min.
    private final long expirationTime = 1000 * 60 * 60;

    public String generateToken(String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();

        System.out.println("JWT token generat: " + token);
        System.out.println("Key used for signing: " + key);

        return token;
    }

    public String extractUsername(String token) {
        try {
            System.out.println("Validating token: " + token);
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            System.out.println("Error while parsing JWT: " + e.getMessage());
            throw e;
        }
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
