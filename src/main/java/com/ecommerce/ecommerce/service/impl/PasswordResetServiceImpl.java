package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.ForgotPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResendCodeRequestDTO;
import com.ecommerce.ecommerce.dto.request.ResetPasswordRequestDTO;
import com.ecommerce.ecommerce.dto.request.VerifyCodeRequestDTO;
import com.ecommerce.ecommerce.dto.response.ResetCodeDataDTO;
import com.ecommerce.ecommerce.dto.response.UserDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.*;
import com.ecommerce.ecommerce.service.EmailService;
import com.ecommerce.ecommerce.service.PasswordResetService;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private static final String RESET_CODE_PREFIX = "pwd_reset:";

    @Value("${recovery.password.code.expiration}")
    private Long CODE_EXPIRATION;

    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final EmailService emailService;

    public PasswordResetServiceImpl(UserService userService, RedisTemplate<String, Object> redisTemplate, EmailService emailService){
        this.userService = userService;
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
    }

    @Override
    public void sendResetCode(String email){
        User user = this.userService.findByUsername(email).orElseThrow(() -> new ObjectNotFoundException("Usuario con email " + email + " no encontrado"));

        String code = generateSixDigitCode();

        ResetCodeDataDTO resetCodeDataDTO = new ResetCodeDataDTO(
                user.getUsername(),
                user.getId(),
                code,
                LocalDateTime.now(),
                0
        );

        // Guardar en Redis con el código como clave. Esto permite buscar por código directamente
        String key = RESET_CODE_PREFIX + code;
        redisTemplate.opsForValue().set(key, resetCodeDataDTO, CODE_EXPIRATION, TimeUnit.MINUTES);

        emailService.sendResetEmail(user.getUsername(), code);
    }

    @Override
    public String verifyResetCode(VerifyCodeRequestDTO verifyCodeRequestDTO){

        String key = RESET_CODE_PREFIX + verifyCodeRequestDTO.getCode();
        ResetCodeDataDTO resetData = (ResetCodeDataDTO) redisTemplate.opsForValue().get(key);

        if (resetData == null) {
            throw new InvalidCodeException("Código inválido o expirado");
        }

        resetData.setAttempts(resetData.getAttempts() + 1);

        if (resetData.getAttempts() > 3) {
            redisTemplate.delete(key);
            throw new TooManyAttemptsException("Demasiados intentos. Solicita un nuevo código");
        }

        // Actualizar en Redis
        redisTemplate.opsForValue().set(key, resetData, CODE_EXPIRATION, TimeUnit.MINUTES);

        // Generar token temporal para cambiar contraseña (válido 10 minutos)
        String resetToken = UUID.randomUUID().toString();
        String tokenKey = "pwd_reset_token:" + resetToken;

        // Guardar token asociado al userId
        redisTemplate.opsForValue().set(tokenKey, resetData.getUserId(), 10, TimeUnit.MINUTES);

        // Eliminar el código ya usado
        redisTemplate.delete(key);

        return resetToken; // Este token se usa en la pantalla 3
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO){
        String tokenKey = "pwd_reset_token:" + resetPasswordRequestDTO.getResetToken();
        Object value = redisTemplate.opsForValue().get(tokenKey);

        if (value == null) {
            throw new InvalidTokenException("Token inválido o expirado");
        }

        Long userId = ((Number) value).longValue();

        // Buscar usuario y cambiar contraseña
        UserDTO user = userService.updatePasswordForRecovery(userId, resetPasswordRequestDTO.getNewPassword());

        // Eliminar token usado
        redisTemplate.delete(tokenKey);

        emailService.sendPasswordChangedEmail(user.getUsername());
    }


    private String generateSixDigitCode() {
        return String.format("%06d", new SecureRandom().nextInt(999999));
    }
}
