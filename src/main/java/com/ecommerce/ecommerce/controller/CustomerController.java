package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final AuthenticationService authenticationService;

    public CustomerController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<RegisterUserDTO> register(@RequestBody @Valid SaveUserDTO saveUserDTO){
        RegisterUserDTO registerUser = authenticationService.registerCustomer(saveUserDTO);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }
}
