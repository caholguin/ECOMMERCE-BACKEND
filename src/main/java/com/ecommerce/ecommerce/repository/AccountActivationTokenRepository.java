package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.AccountActivationToken;
import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountActivationTokenRepository extends JpaRepository<AccountActivationToken, Long> {

    Optional<AccountActivationToken> findByToken(String token);

    Optional<AccountActivationToken> findByUserAndUsedFalse(User user);

    // Para limpiar tokens expirados (usar con @Scheduled)
    void deleteByExpiresAtBefore(LocalDateTime dateTime);

    // Para evitar que un usuario tenga múltiples tokens activos
    void deleteByUser(User user);
}
