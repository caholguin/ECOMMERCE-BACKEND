package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ForgotPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResendCodeRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResetPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.VerifyCodeRequestDTO;
import com.ecommerce.ecommerce.dto.response.MessageResponseDTO;
import com.ecommerce.ecommerce.dto.response.VerifyCodeResponseDTO;
import com.ecommerce.ecommerce.service.PasswordResetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService){
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO request){
        passwordResetService.sendResetCode(request.getEmail());
        return new ResponseEntity<>(new MessageResponseDTO("Código de recuperación de contraseña enviado"),HttpStatus.OK);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeRequestDTO request) {
            String resetToken = passwordResetService.verifyResetCode(request);
            return new ResponseEntity<>(new VerifyCodeResponseDTO(resetToken),HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
            passwordResetService.resetPassword(request);
            return new ResponseEntity<>(new MessageResponseDTO("Contraseña actualizada correctamente"),HttpStatus.OK);
    }
}













