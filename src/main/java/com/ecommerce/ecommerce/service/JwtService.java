package com.ecommerce.ecommerce.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails user, Map<String, Object> extractClaims);

    String extractUsername(String jwt);

    String extractJwtFromRequest(HttpServletRequest request);

    Date extractExpiration(String jwt);
}
