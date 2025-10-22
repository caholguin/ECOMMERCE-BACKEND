package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ResendActivationDTO;
import com.ecommerce.ecommerce.entity.User;

public interface TokenService {

    String generateActivationToken(User user);

    void activateAccount(String token);

    void resendActivation(ResendActivationDTO resendActivationDTO);

    void cleanExpiredTokens();
}
