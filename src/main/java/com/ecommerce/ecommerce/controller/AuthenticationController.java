package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.LoginRequestDTO;
import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        LoginResponseDTO response = authenticationService.login(loginRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return new ResponseEntity<>(isTokenValid,HttpStatus.OK);
    }

}
