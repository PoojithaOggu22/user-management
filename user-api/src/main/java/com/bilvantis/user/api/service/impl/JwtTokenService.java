package com.bilvantis.user.api.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenService {

    private static final String SECRET = "4027d080f9a3954189ebca5ddbbfdfc9cb824314c0d6c910eff8bec1c689eabc";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String employeeId) {
        final String username = extractUsername(token);
        return (username.equals(employeeId) && !isTokenExpired(token));
    }
    /**
     * Generate Token
     *
     * @param employeeId EmployeeId
     * @return Token
     */
    public String generateToken(String employeeId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, employeeId);
    }

    /**
     * Create token
     *
     * @param claims Claims
     * @param employeeId EmployeeId
     * @return Token
     */
    private String createToken(Map<String, Object> claims, String employeeId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(employeeId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Get Sign Key
     *
     * @return Key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
