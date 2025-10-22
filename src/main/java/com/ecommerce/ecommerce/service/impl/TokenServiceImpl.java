package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.ResendActivationDTO;
import com.ecommerce.ecommerce.entity.AccountActivationToken;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.InvalidTokenException;
import com.ecommerce.ecommerce.exception.TokenExpiredException;
import com.ecommerce.ecommerce.repository.AccountActivationTokenRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.EmailService;
import com.ecommerce.ecommerce.service.TokenService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${activation.account.token.expiration-hours}")
    private int tokenExpirationHours;

    private final AccountActivationTokenRepository accountActivationTokenRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final UserRepository userRepository;

    public TokenServiceImpl(AccountActivationTokenRepository accountActivationTokenRepository, EmailService emailService, UserService userService, UserRepository userRepository){
        this.accountActivationTokenRepository = accountActivationTokenRepository;

        this.emailService = emailService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String generateActivationToken(User user){
        // 1. Eliminar tokens anteriores del usuario (si existen)
        accountActivationTokenRepository.deleteByUser(user);

        // 2. Generar token único (UUID es suficientemente seguro)
        String token = UUID.randomUUID().toString();

        // 3. Calcular fecha de expiración
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(tokenExpirationHours);

        // 4. Crear y guardar token
        AccountActivationToken activationToken = new AccountActivationToken(user, token, expiresAt);
        accountActivationTokenRepository.save(activationToken);

        return token;
    }

    @Override
    @Transactional
    public void activateAccount(String token){

        // 1. Buscar token
        AccountActivationToken activationToken = accountActivationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token inválido o no existe"));

        // 2. Validar que no esté usado
        if (activationToken.isUsed()) {
            throw new InvalidTokenException("Esta cuenta ya fue activada");
        }

        // 3. Validar que no esté expirado
        if (activationToken.isExpired()) {
            throw new TokenExpiredException("El token ha expirado. Solicita uno nuevo");
        }

        // 4. Activar usuario
        User user = activationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        // 5. Marcar token como usado
        activationToken.setUsed(true);
        activationToken.setConfirmedAt(LocalDateTime.now());

        accountActivationTokenRepository.save(activationToken);

    }

    @Override
    @Transactional
    public void resendActivation(ResendActivationDTO resendActivationDTO){
        userService.findByUsername(resendActivationDTO.getEmail()).ifPresent(user -> {
            if (!user.isEnabled()) {
                String activationToken = this.generateActivationToken(user);
                emailService.sendActivationEmailAsync(user.getUsername(), activationToken);
            }
        });
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanExpiredTokens(){
        LocalDateTime now = LocalDateTime.now();
        accountActivationTokenRepository.deleteByExpiresAtBefore(now);
    }
}
