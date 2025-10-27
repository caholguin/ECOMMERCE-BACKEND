package com.ecommerce.ecommerce.service;

public interface EmailService {

    void sendActivationEmailAsync(String toEmail, String activationToken);

    void sendResetEmail(String email, String code);

    void sendPasswordChangedEmail(String email);
}
