package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.RegisteredUserDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.AuthenticationService;
import com.ecommerce.ecommerce.service.JwtService;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserService userService, JwtService jwtService){
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public RegisteredUserDTO registerCustomer(SaveUserDTO saveUserDTO){

        User user = userService.registerCustomer(saveUserDTO);

        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
        registeredUserDTO.setId(user.getId());
        registeredUserDTO.setName(user.getName());
        registeredUserDTO.setUsername(user.getUsername());
        registeredUserDTO.setEmail(user.getEmail());
        registeredUserDTO.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        registeredUserDTO.setJwt(jwt);

        return registeredUserDTO;
    }

    private Map<String, Object> generateExtraClaims(User user){

        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }
}
