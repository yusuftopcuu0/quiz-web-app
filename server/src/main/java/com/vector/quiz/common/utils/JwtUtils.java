package com.vector.quiz.common.utils;

import com.vector.quiz.modules.user.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    public int getJwtRefreshExpirationMs() {
        return jwtRefreshExpirationMs;
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("role", "ROLE_" + user.getRole());
        claimsMap.put("email", user.getEmail());
        claimsMap.put("sub", user.getUsername());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            // Token süresi geçmiş
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            // Geçersiz format, imza hatası vb.
        }
        return false;
    }
}
