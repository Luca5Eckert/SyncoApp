package com.api.blog.infrastructure.security.jwt;

import com.api.blog.infrastructure.exception.token.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final Key key;
    private final long validityInMilliseconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token.validity}") long validityInMilliseconds
    ) {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT secret must not be null or empty");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    /**
     * Gera um token JWT para um usuário, incluindo o email.
     * @param email O email de usuário para o qual o token será gerado.
     * @return O token JWT como uma string.
     */
    public String generateToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("email", email);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida um token JWT (compatível com a API anterior).
     * @param token O token JWT a ser validado.
     * @return true se o token for válido, false caso contrário.
     */
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (TokenInvalidException e) {
            logger.debug("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Faz o parse das claims do token e aplica validações de integridade/expiração.
     * Em caso de falha lança TokenInvalidException com a causa apropriada.
     * @param token O token JWT.
     * @return Claims do token (se válido).
     * @throws TokenInvalidException quando o token é inválido/expirado/malformado.
     */
    public Claims parseClaims(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new TokenInvalidException("Token is null or empty");
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenInvalidException("Token expired");

        } catch (UnsupportedJwtException e) {
            throw new TokenInvalidException("Unsupported token");

        } catch (MalformedJwtException e) {
            throw new TokenInvalidException("Malformed token");

        } catch (SignatureException e) {
            throw new TokenInvalidException("Invalid token signature");

        } catch (IllegalArgumentException e) {
            throw new TokenInvalidException("Token string is empty or has illegal arguments");

        }

    }

    /**
     * Obtém o email do usuário de um token JWT.
     * @param token O token JWT.
     * @return O email do usuário.
     * @throws TokenInvalidException lança quando token é inválido
     */
    public String getUserEmail(String token) {
        Claims claims = parseClaims(token);

        String email = claims.get("email", String.class);
        if (email == null || email.trim().isEmpty()) {
            email = claims.getSubject();
        }

        if (email == null || email.trim().isEmpty()) {
            throw new TokenInvalidException("Email not present in token claims");
        }

        return email;
    }
}