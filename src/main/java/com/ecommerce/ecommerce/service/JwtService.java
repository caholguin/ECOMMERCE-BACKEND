package com.ecommerce.ecommerce.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails user, Map<String, Object> extractClaims);

    String extractUsername(String jwt);
}
