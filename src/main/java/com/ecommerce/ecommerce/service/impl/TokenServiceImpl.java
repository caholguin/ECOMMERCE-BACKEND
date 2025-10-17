package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.AccountActivationToken;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.InvalidTokenException;
import com.ecommerce.ecommerce.exception.TokenExpiredException;
import com.ecommerce.ecommerce.repository.AccountActivationTokenRepository;
import com.ecommerce.ecommerce.service.TokenService;
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

    public TokenServiceImpl(AccountActivationTokenRepository accountActivationTokenRepository){
        this.accountActivationTokenRepository = accountActivationTokenRepository;
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
            throw new InvalidTokenException("Este token ya fue utilizado");
        }

        // 3. Validar que no esté expirado
        if (activationToken.isExpired()) {
            throw new TokenExpiredException("El token ha expirado. Solicita uno nuevo");
        }

        // 4. Activar usuario
        User user = activationToken.getUser();
        user.setEnabled(true); // o user.setAccountStatus(AccountStatus.ACTIVE);

        // 5. Marcar token como usado
        activationToken.setUsed(true);
        activationToken.setConfirmedAt(LocalDateTime.now());

        accountActivationTokenRepository.save(activationToken);
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanExpiredTokens(){
        LocalDateTime now = LocalDateTime.now();
        accountActivationTokenRepository.deleteByExpiresAtBefore(now);
    }
}
