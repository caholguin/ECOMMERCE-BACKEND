package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.LoginRequestDTO;
import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.dto.response.RegisteredUserDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.AuthenticationService;
import com.ecommerce.ecommerce.service.JwtService;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(loginRequestDTO.getUsername()).get();

        String jwt = jwtService.generateToken(user,generateExtraClaims((User) user));

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setJwt(jwt);

        return loginResponseDTO;
    }

    @Override
    public boolean validateToken(String jwt){

        try{
        jwtService.extractUsername(jwt);
        return true;
        } catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            return false;
        }
    }
}
