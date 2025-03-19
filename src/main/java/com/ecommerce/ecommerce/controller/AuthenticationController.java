package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.LoginRequestDTO;
import com.ecommerce.ecommerce.dto.request.RefreshTokenDTO;
import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.dto.response.LogoutResponseDTO;
import com.ecommerce.ecommerce.dto.response.UserDTO;
import com.ecommerce.ecommerce.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(HttpServletRequest request){
        authenticationService.logout(request);
        return new ResponseEntity<>(new LogoutResponseDTO("Logout exitoso"),HttpStatus.OK);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return new ResponseEntity<>(isTokenValid,HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO){
        System.out.println("jwt = " + refreshTokenDTO);
        LoginResponseDTO resp = authenticationService.refreshToken(refreshTokenDTO.getToken());
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> findMyProfile(){
        UserDTO user = authenticationService.findLoggedInUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
