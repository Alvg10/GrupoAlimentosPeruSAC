package com.grupo.alimentos.peru.config.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import com.grupo.alimentos.peru.config.JwtConfig;

@Service
@RequiredArgsConstructor
public class JwtService {

   private final JwtConfig jwtConfig;

   private SecretKey getSignInKey() {
    return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getTimeExpiration()))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)   
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
    return extractAllClaims(token)
            .getExpiration()
            .before(new Date());
}

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }
}
