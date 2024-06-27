package com.security.config.JwtUtil;


import com.security.model.entities.PermissionEntity;
import com.security.model.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.stream.Collectors;


@Component
@Slf4j
public class JwtHelper {

    @Value("${security.jwt.key.private}")
    private String privateKey;
    @Value("${security.jwt.expiration}")
    private Long expiration;


    public String getUserNameFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date getTokenExpiration(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
                log.error("invalid token", e);
            return false;
        }
    }


    public String generateToken(UserEntity userEntity) {
        Map<String, Object> claims = new HashMap<>();

        Set<String> roles = userEntity.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet());
        claims.put("roles", roles);

        Set<String> permissions = userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(PermissionEntity::getName)
                .collect(Collectors.toSet());
        claims.put("permissions", permissions);

        return createToken(claims, userEntity.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }
}

