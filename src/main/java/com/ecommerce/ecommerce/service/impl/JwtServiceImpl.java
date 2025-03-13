package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(UserDetails user,Map<String, Object> extractClaims){

        Date issuedtAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issuedtAt.getTime());

        String jwt = Jwts.builder()
                .header().add("typ", "JWT").and()
                .claims(extractClaims)
                .subject(user.getUsername())
                .issuedAt(issuedtAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();

        return jwt;
    }

    private SecretKey generateKey(){
        byte[] secretKeyDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretKeyDecoded);
    }

    @Override
    public String extractUsername(String jwt){
        return extractAllClaims(jwt).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
